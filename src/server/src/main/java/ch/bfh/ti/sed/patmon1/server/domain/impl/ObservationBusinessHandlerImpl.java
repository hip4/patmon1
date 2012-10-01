package ch.bfh.ti.sed.patmon1.server.domain.impl;

import java.util.Date;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.ObservationPeriodDefinitionBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceAlreadyAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.gtk.DeviceGatekeeper;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

public class ObservationBusinessHandlerImpl extends
		AbstractContextAwarableBusinessHandler implements
		ObservationPeriodDefinitionBusinessHandler {

	private PatientSBO patient;

	private DeviceSBO device;

	private DeviceGatekeeper deviceGatekeeper;

	private ObservationPeriodSBO observationPeriod;

	protected ObservationBusinessHandlerImpl(ApplicationContext context,
			PatientSBO patient, DeviceSBO device) {
		super(context);
		this.patient = patient;
		this.device = device;
	}

	@Override
	public void createAndInitializeObservationPeriod(Date periodStart,
			Date periodEnd, int measurementIntervalInSeconds)
			throws DeviceAlreadyAssignedBusinessException {
		checkCurrentlyAssigned();
		observationPeriod = createObservationPeriod(periodStart, periodEnd,
				measurementIntervalInSeconds);
		getApplicationContext().getEntityManager().persistObservationPeriod(
				observationPeriod);
		GatekeeperFactory gf = getApplicationContext().getGatekeeperFactory();
		deviceGatekeeper = gf.createDeviceGatekeeper(device);
		deviceGatekeeper.init(observationPeriod);
	}

	/**
	 * Creates a new {@link ObservationPeriodSBO}.
	 * 
	 * @param periodStart
	 *            the period start to set
	 * @param periodEnd
	 *            the period end to set
	 * @param measurementIntervalInSeconds
	 *            the interval of measurements in seconds to set
	 * @return an newly created {@link ObservationPeriodSBO}
	 */
	private ObservationPeriodSBO createObservationPeriod(Date periodStart,
			Date periodEnd, int measurementIntervalInSeconds) {
		ObservationPeriodSBO period = new ObservationPeriodSBO();
		period.setStart(periodStart);
		period.setEnd(periodEnd);
		period.setMeasurementInterval(measurementIntervalInSeconds);
		period.setPatient(patient);
		period.assignDevice(device);
		return period;
	}

	/**
	 * Checks if a device is already assigned to a patient. Throws a
	 * DeviceAlreadyAssignedBusinessException if true.
	 * 
	 * @throws DeviceAlreadyAssignedBusinessException
	 *             if the device is already assigned
	 */
	private void checkCurrentlyAssigned()
			throws DeviceAlreadyAssignedBusinessException {
		if (device.isCurrentlyAssigned()) {
			throw new DeviceAlreadyAssignedBusinessException();
		}
	}

	@Override
	public Measurement testMeasure() {
		return deviceGatekeeper.triggerMeasurement();
	}
}
