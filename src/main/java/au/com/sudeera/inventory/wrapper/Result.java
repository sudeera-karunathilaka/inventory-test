package au.com.sudeera.inventory.wrapper;

/**
 * The Class Result. Act as super Result for API outputs
 */
public class Result {
	
	/** The type. */
	protected ResultType type;
	
	/** The msg. */
	protected String msg;

	/**
	 * Instantiates a new result.
	 */
	public Result() {
		super();
	}

	/**
	 * Instantiates a new result.
	 *
	 * @param type the type
	 * @param msg the msg
	 */
	public Result(ResultType type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ResultType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ResultType type) {
		this.type = type;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
