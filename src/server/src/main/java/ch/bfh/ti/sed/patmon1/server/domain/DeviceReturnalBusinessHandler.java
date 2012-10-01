package ch.bfh.ti.sed.patmon1.server.domain;

import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceNotAssignedBusinessException;

/**
 * Handler for device returnal.
 */
public interface DeviceReturnalBusinessHandler {

	/**
	 * Returns the device and unassigns it from the observation period.
	 * 
	 * @throws DeviceNotAssignedBusinessException
	 *             if the device is not assigned to an observation period
	 */
	public void returnDevice() throws DeviceNotAssignedBusinessException;

}
