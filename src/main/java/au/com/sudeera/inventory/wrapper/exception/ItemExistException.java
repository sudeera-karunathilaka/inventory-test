package au.com.sudeera.inventory.wrapper.exception;

/**
 * The Class ItemExistException. Generic exception to handle the existing/none existing items. 
 */
public class ItemExistException extends RuntimeException {

	/**
	 * Instantiates a new item exist exception.
	 *
	 * @param message the message
	 */
	public ItemExistException(String message) {
		super(message);
	}

}
