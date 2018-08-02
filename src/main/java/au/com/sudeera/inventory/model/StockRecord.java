package au.com.sudeera.inventory.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * The entity StockRecord.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class StockRecord extends BaseEntity implements Comparable<StockRecord> {

	/** The item. */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Item item;

	/** The quantity. */
	private Long quantity;

	/**
	 * Instantiates a new stock record.
	 */
	public StockRecord() {

	}

	/**
	 * Instantiates a new stock record.
	 *
	 * @param quantity the quantity
	 */
	public StockRecord(Long quantity) {
		super();
		this.quantity = quantity;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.model.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.model.BaseEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockRecord other = (StockRecord) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StockRecord o) {
		return this.getCreated().compareTo(o.getCreated());
	}

}
