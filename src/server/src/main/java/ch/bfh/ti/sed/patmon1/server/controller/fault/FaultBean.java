package ch.bfh.ti.sed.patmon1.server.controller.fault;

/**
 * FaultBean which is used by JAX-WS to send custom Exceptions.
 */
public class FaultBean {

	private String message;

	/**
	 * Empty constructor.
	 */
	public FaultBean() {
	}

	/**
	 * Constructor which sets the message
	 * 
	 * @param message
	 *            the message to set
	 */
	public FaultBean(String message) {
		this.message = message;
	}

	/**
	 * @return the fault message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
