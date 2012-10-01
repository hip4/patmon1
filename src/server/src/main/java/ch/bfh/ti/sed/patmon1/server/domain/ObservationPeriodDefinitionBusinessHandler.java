package ch.bfh.ti.sed.patmon1.server.domain;

import java.util.Date;

import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceAlreadyAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;

/**
 * Handler for defining ObservationPeriods.
 */
public interface ObservationPeriodDefinitionBusinessHandler {

	/**
	 * Creates an ObservationPeriod in the system and initializes the assigned
	 * device.
	 * 
	 * @param periodStart
	 *            the period start to set
	 * @param periodEnd
	 *            the period end to set
	 * @param measurementIntervalInSeconds
	 *            the interval of measurements in seconds to set
	 * @throws DeviceAlreadyAssignedBusinessException
	 *             if the affected device is already assigned to a patient
	 */
	public void createAndInitializeObservationPeriod(Date periodStart,
			Date periodEnd, int measurementIntervalInSeconds)
			throws DeviceAlreadyAssignedBusinessException;

	/**
	 * Triggers a test-measurement on the device.
	 * 
	 * @return the result from the device
	 */
	public Measurement testMeasure();

}
