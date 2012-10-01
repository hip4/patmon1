package ch.bfh.ti.sed.patmon1.server.usecase.adapter;

import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

/**
 * Adapter class for {@link EntityManager}. Throws an
 * {@link UnsupportedOperationException} on every method invocation. Test
 * classes should override every used method.
 * 
 * @author burgc5
 */
public class EntityManagerAdapter implements EntityManager {

	private static final String MESSAGE = "This method should only be called if overridden";

	@Override
	public DoctorSBO persistDoctor(DoctorSBO doctor) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public PatientSBO persistPatient(PatientSBO patient) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public ObservationPeriodSBO persistObservationPeriod(
			ObservationPeriodSBO observationPeriod) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public DeviceSBO persistDevice(DeviceSBO device) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public MeasurementSBO persistMeasurement(MeasurementSBO measurement) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<PatientSBO> getPatients() {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<ObservationPeriodSBO> getObservationPeriods() {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<ObservationPeriodSBO> findObservationPeriods(PatientSBO patient) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<DoctorSBO> getDoctors() {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<MeasurementSBO> getMeasurements() {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<DeviceSBO> getDevices() {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public DeviceSBO findDeviceById(int id) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public DoctorSBO getDoctor(String username) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public PatientSBO findPatientById(String ssn) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public ObservationPeriodSBO findObservationPeriodById(
			int observationPeriodId) {
		throw new UnsupportedOperationException(MESSAGE);
	}

	@Override
	public Set<MeasurementSBO> findMeasurements(
			ObservationPeriodSBO observationPeriod) {
		throw new UnsupportedOperationException(MESSAGE);
	}

}
