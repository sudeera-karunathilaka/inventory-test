package au.com.sudeera.inventory.service;

import java.util.List;

import au.com.sudeera.inventory.model.Item;
import au.com.sudeera.inventory.model.StockRecord;

/**
 * The Interface InventoryService. Interface for business layer logic.
 */
public interface InventoryService {

	/**
	 * Creates the item.
	 *
	 * @param item the item
	 * @return the item
	 */
	Item createItem(Item item);

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	List<Item> getItems();

	/**
	 * Gets the stock history.
	 *
	 * @param itemCode the item code
	 * @return the stock history
	 */
	List<StockRecord> getStockHistory(String itemCode);

	/**
	 * Update item.
	 *
	 * @param item the item
	 * @return the item
	 */
	Item updateItem(Item item);

	/**
	 * Gets the item by code.
	 *
	 * @param code the code
	 * @return the item by code
	 */
	Item getItemByCode(String code);
}
