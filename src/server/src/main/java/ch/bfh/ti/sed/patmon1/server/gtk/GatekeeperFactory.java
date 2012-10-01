package ch.bfh.ti.sed.patmon1.server.gtk;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;

/**
 * Factory for the system's gatekeepers.
 */
public interface GatekeeperFactory {

	/**
	 * Creates and returns a gatekeeper to the given device.
	 * 
	 * @param device
	 *            the device to open a gatekeeper to
	 * @return the created gatekeeper.
	 */
	public DeviceGatekeeper createDeviceGatekeeper(DeviceSBO device);

}
