package au.com.sudeera.inventory.wrapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

/**
 * The Class ItemWrapper. Wrapper for item data.
 */
public class ItemWrapper {

	/** The code. */
	@NonNull
	@NotEmpty
	private String code;

	/** The name. */
	@NonNull
	@NotEmpty
	private String name;

	/** The description. */
	private String description;

	/** The quantity. */
	@NonNull
	@Min(value = 1, message = "must be > 0")
	private Long quantity;

	/**
	 * Instantiates a new item wrapper.
	 */
	public ItemWrapper() {

	}

	/**
	 * Instantiates a new item wrapper.
	 *
	 * @param code the code
	 * @param name the name
	 * @param description the description
	 * @param quantity the quantity
	 */
	public ItemWrapper(String code, String name, String description, Long quantity) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
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

}