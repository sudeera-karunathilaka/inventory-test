package au.com.sudeera.inventory.factory;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import au.com.sudeera.inventory.model.Item;
import au.com.sudeera.inventory.model.StockRecord;
import au.com.sudeera.inventory.wrapper.ItemHistoryWrapper;
import au.com.sudeera.inventory.wrapper.ItemWrapper;
import au.com.sudeera.inventory.wrapper.StockRecordWrapper;

/**
 * A factory for converting <code>Item</code> to <code>ItemWrapper</code> and vice versa.
 */
@Service
public class ConverterFactory {

	/**
	 * Convert to item.
	 *
	 * @param itemWrapper the item wrapper
	 * @return the item
	 */
	public Item convertToItem(ItemWrapper itemWrapper) {
		StockRecord stockRecord = new StockRecord(itemWrapper.getQuantity());
		Item item = new Item();
		BeanUtils.copyProperties(itemWrapper, item, "quantity");
		item.setStockRecord(Collections.singletonList(stockRecord));
		return item;
	}

	/**
	 * Convert to item wrapper.
	 *
	 * @param item the item
	 * @return the item wrapper
	 */
	public ItemWrapper convertToItemWrapper(Item item) {
		if (item.getStockRecord().size() > 1) {
			item.getStockRecord()
					.sort((StockRecord sr1, StockRecord sr2) -> sr2.getCreated().compareTo(sr1.getCreated()));
		}
		ItemWrapper itemWrapper = new ItemWrapper();
		BeanUtils.copyProperties(item, itemWrapper, "quantity");
		itemWrapper.setQuantity(item.getStockRecord() == null ? 0 : item.getStockRecord().get(0).getQuantity());
		return itemWrapper;
	}

	/**
	 * Convert to item history wrapper.
	 *
	 * @param item the item
	 * @return the item history wrapper
	 */
	public ItemHistoryWrapper convertToItemHistoryWrapper(Item item) {
		ItemHistoryWrapper itemHistoryWrapper = new ItemHistoryWrapper();
		itemHistoryWrapper.setItem(convertToItemWrapper(item));
		itemHistoryWrapper.setStockRecords(item.getStockRecord().stream()
				.map(stockRecord -> convertToStockRecordWrapper(stockRecord)).collect(Collectors.toList()));

		return itemHistoryWrapper;
	}

	/**
	 * Convert to stock record wrapper.
	 *
	 * @param stockRecord the stock record
	 * @return the stock record wrapper
	 */
	private StockRecordWrapper convertToStockRecordWrapper(StockRecord stockRecord) {
		StockRecordWrapper stockRecordWrapper = new StockRecordWrapper();
		BeanUtils.copyProperties(stockRecord, stockRecordWrapper, "created");

		stockRecordWrapper.setCreated(stockRecord.getCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		return stockRecordWrapper;
	}

}
