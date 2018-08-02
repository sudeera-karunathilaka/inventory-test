package au.com.sudeera.inventory.wrapper;

import java.util.List;

/**
 * The Class ItemHistoryWrapper.
 */
public class ItemHistoryWrapper {

	/** The item. */
	private ItemWrapper item;

	/** The stock records. */
	private List<StockRecordWrapper> stockRecords;

	/**
	 * Instantiates a new item history wrapper.
	 */
	public ItemHistoryWrapper() {
		super();
	}

	/**
	 * Instantiates a new item history wrapper.
	 *
	 * @param item the item
	 * @param stockRecords the stock records
	 */
	public ItemHistoryWrapper(ItemWrapper item, List<StockRecordWrapper> stockRecords) {
		super();
		this.item = item;
		this.stockRecords = stockRecords;
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public ItemWrapper getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(ItemWrapper item) {
		this.item = item;
	}

	/**
	 * Gets the stock records.
	 *
	 * @return the stock records
	 */
	public List<StockRecordWrapper> getStockRecords() {
		return stockRecords;
	}

	/**
	 * Sets the stock records.
	 *
	 * @param stockRecords the new stock records
	 */
	public void setStockRecords(List<StockRecordWrapper> stockRecords) {
		this.stockRecords = stockRecords;
	}

}
