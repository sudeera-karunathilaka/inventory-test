package au.com.sudeera.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.sudeera.inventory.model.Item;

/**
 * The Interface ItemRepository.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

	/**
	 * Find item by code. This method is to use when it is necessary to fetch the related stock records.
	 *
	 * @param itemCode the item code
	 * @return the item
	 */
	@Query(value = "SELECT DISTINCT i FROM Item i JOIN FETCH i.stockRecord WHERE i.code=:itemCode")
	Item findByCode(@Param(value = "itemCode") String itemCode);

	/**
	 * Find all items.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT DISTINCT i FROM Item i JOIN FETCH i.stockRecord")
	List<Item> findAllItems();

}
