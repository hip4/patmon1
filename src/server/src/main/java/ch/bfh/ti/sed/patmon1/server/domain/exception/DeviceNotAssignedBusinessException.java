package ch.bfh.ti.sed.patmon1.server.domain.exception;

public class DeviceNotAssignedBusinessException extends Exception {

	private static final long serialVersionUID = 7538110674964441839L;

	private static final String MESSAGE = "The device is not assigned to the observation period!";

	public DeviceNotAssignedBusinessException() {
		super(MESSAGE);
	}
}
