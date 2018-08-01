package au.com.sudeera.inventory.wrapper;

/**
 * The Class StockRecordWrapper. Wrapper for stock record.
 */
public class StockRecordWrapper {

	/** The quantity. */
	private Long quantity;
	
	/** The created. */
	private String created;

	/**
	 * Instantiates a new stock record wrapper.
	 */
	public StockRecordWrapper() {

	}

	/**
	 * Instantiates a new stock record wrapper.
	 *
	 * @param quantity the quantity
	 * @param created the created
	 */
	public StockRecordWrapper(Long quantity, String created) {
		super();
		this.quantity = quantity;
		this.created = created;
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
	 * Gets the created.
	 *
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(String created) {
		this.created = created;
	}

}
