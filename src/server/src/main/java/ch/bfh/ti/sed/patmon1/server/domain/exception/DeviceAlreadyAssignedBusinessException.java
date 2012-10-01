package ch.bfh.ti.sed.patmon1.server.domain.exception;

public class DeviceAlreadyAssignedBusinessException extends Exception {

	private static final long serialVersionUID = -145931009088197343L;
	
	private static final String MESSAGE = "The device is already in use!";

	public DeviceAlreadyAssignedBusinessException() {
		super(MESSAGE);
	}
}
