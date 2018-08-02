package au.com.sudeera.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * The entity Item. Since it is required to track the changes done to the
 * quantity, a <code>List</code> of <code>StockRecord</code> are kept in a
 * separate table with One-To-Many mapping.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends BaseEntity {

	/** The code. */
	private String code;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The stock record. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
	private List<StockRecord> stockRecord;

	/**
	 * Instantiates a new item.
	 *
	 * @param code the code
	 * @param name the name
	 * @param description the description
	 * @param stockRecord the stock record
	 */
	public Item(String code, String name, String description, List<StockRecord> stockRecord) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.stockRecord = stockRecord;
	}

	/**
	 * Instantiates a new item.
	 */
	public Item() {
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the stock record.
	 *
	 * @return the stock record
	 */
	public List<StockRecord> getStockRecord() {
		return stockRecord;
	}

	/**
	 * Sets the stock record.
	 *
	 * @param stockRecord the new stock record
	 */
	public void setStockRecord(List<StockRecord> stockRecord) {
		this.stockRecord = stockRecord;
	}

	/* (non-Javadoc)
	 * @see au.com.sudeera.surefire.model.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Item other = (Item) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
