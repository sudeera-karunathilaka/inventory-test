package au.com.sudeera.inventory.wrapper;

import java.util.List;

/**
 * The Class ErrorResult. Act as Error Result for API outputs
 */
public class ErrorResult extends Result {
	
	/** The field errors. */
	private List<String> fieldErrors;

	/**
	 * Gets the field errors.
	 *
	 * @return the field errors
	 */
	public List<String> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * Sets the field errors.
	 *
	 * @param fieldErrors the new field errors
	 */
	public void setFieldErrors(List<String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
