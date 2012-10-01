package ch.bfh.ti.sed.patmon1.server.gtk;

import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;

/**
 * Gatekeeper to the physical devices.
 */
public interface DeviceGatekeeper {

	/**
	 * Initializes a device with an observation period.
	 * 
	 * @param device
	 *            the device to initialize
	 * @param period
	 *            the period data to set
	 */
	public void init(ObservationPeriodSBO period);

	/**
	 * Triggers a forced measurement on the device and returns the Measurement.
	 * 
	 * @param device
	 *            the device to trigger a measurement from
	 * @return the measurement
	 */
	public Measurement triggerMeasurement();

}
