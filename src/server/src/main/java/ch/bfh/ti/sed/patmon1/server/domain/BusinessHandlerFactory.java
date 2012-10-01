package ch.bfh.ti.sed.patmon1.server.domain;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

/**
 * Factory for stateful business handlers.
 */
public interface BusinessHandlerFactory {

	/**
	 * Creates a business handler for defining observation periods and assign
	 * them do a device.
	 * 
	 * @param patient
	 *            the patient which is assigned the device to
	 * @param device
	 *            the device to assign
	 * @return a new stateful {@link ObservationPeriodDefinitionBusinessHandler}
	 */
	public ObservationPeriodDefinitionBusinessHandler createObservationHandler(
			PatientSBO patient, DeviceSBO device);

	/**
	 * Creates a business handler for accessing measurements.
	 * 
	 * @param patient
	 *            the patient to get measurements from
	 * @return a new stateful {@link MeasurementBusinessHandler}
	 */
	public MeasurementBusinessHandler createMeasurementHandler(
			PatientSBO patient);

	/**
	 * Creates a business handler for accessing measurements.
	 * 
	 * @param observationPeriod
	 *            the observationPeriod to get measurements from
	 * @return a new stateful {@link MeasurementBusinessHandler}
	 */
	public MeasurementBusinessHandler createMeasurementHandler(
			ObservationPeriodSBO observationPeriod);

	/**
	 * Creates a business handler for returning devices.
	 * 
	 * @param device
	 *            the device to return
	 * @return a new stateful {@link DeviceReturnalBusinessHandler}
	 */
	public DeviceReturnalBusinessHandler createDeviceReturnalHandler(
			DeviceSBO device);

}
