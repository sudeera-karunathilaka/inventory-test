package au.com.sudeera.inventory.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.sudeera.inventory.model.Item;
import au.com.sudeera.inventory.model.StockRecord;
import au.com.sudeera.inventory.repository.ItemRepository;
import au.com.sudeera.inventory.repository.StockRecordRepository;
import au.com.sudeera.inventory.service.InventoryService;
import au.com.sudeera.inventory.wrapper.exception.ItemExistException;

/**
 * The Class InventoryServiceImpl. Implements business layer logic.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	/** The item repository. */
	private ItemRepository itemRepository;

	/** The stock record repository. */
	private StockRecordRepository stockRecordRepository;

	/**
	 * Sets the item repository.
	 *
	 * @param itemRepository the new item repository
	 */
	@Autowired
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	/**
	 * Sets the stock record repository.
	 *
	 * @param stockRecordRepository the new stock record repository
	 */
	@Autowired
	public void setStockRecordRepository(StockRecordRepository stockRecordRepository) {
		this.stockRecordRepository = stockRecordRepository;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.service.InventoryService#createItem(au.com.sudeera.surefire.model.Item)
	 */
	@Override
	@Transactional(value = Transactional.TxType.REQUIRED)
	public Item createItem(Item item) {
		if (itemRepository.findByCode(item.getCode()) != null) {
			throw new ItemExistException("An item with the code " + item.getCode() + " existing.");
		}
		item = itemRepository.save(item);
		//This is to ensure two way binding.
		item.getStockRecord().get(0).setItem(item);
		return item;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.service.InventoryService#getItems()
	 */
	@Override
	@Transactional(value = Transactional.TxType.REQUIRED)
	public List<Item> getItems() {
		List<Item> list = itemRepository.findAllItems();
		return list;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.service.InventoryService#getStockHistory(java.lang.String)
	 */
	@Override
	public List<StockRecord> getStockHistory(String itemCode) {
		return itemRepository.findByCode(itemCode).getStockRecord();
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.service.InventoryService#updateItem(au.com.sudeera.surefire.model.Item)
	 */
	@Override
	@Transactional(value = Transactional.TxType.REQUIRED)
	public Item updateItem(Item item) {
		Item existingItem = itemRepository.findByCode(item.getCode());
		if (existingItem == null) {
			throw new ItemExistException("Cannot find an item with code " + item.getCode() + ".");
		}

		existingItem.setDescription(item.getDescription());
		existingItem.setCode(item.getCode());
		existingItem.setName(item.getName());

		if (item.getStockRecord() != null) {
			if (existingItem.getStockRecord().size() > 1) {
				item.getStockRecord()
						.sort((StockRecord sr1, StockRecord sr2) -> sr2.getCreated().compareTo(sr1.getCreated()));
			}
			StockRecord stockItem = item.getStockRecord().get(0);
			stockItem.setItem(existingItem);
			stockItem = stockRecordRepository.save(stockItem);
			existingItem.getStockRecord().add(stockItem);
		}
		existingItem = itemRepository.save(existingItem);

		return existingItem;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.service.InventoryService#getItemByCode(java.lang.String)
	 */
	@Override
	public Item getItemByCode(String code) {
		Item item = itemRepository.findByCode(code);
		if (item == null) {
			throw new ItemExistException("Cannot find an item with code " + code + ".");
		}
		return item;
	}

}
