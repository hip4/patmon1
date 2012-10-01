package ch.bfh.ti.sed.patmon1.server.persistence.mock;

import java.util.Collections;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

/**
 * A mock-implementation for an {@link EntityManager}.
 */
public class MockEntityManager implements EntityManager {
	private static final String RESSOURCEBUNDLE_FILE = "database";
	private static final String DATABASE_KEY = "use.database";

	private Set<DoctorSBO> doctors = new HashSet<DoctorSBO>();
	private Set<PatientSBO> patients = new HashSet<PatientSBO>();
	private Set<ObservationPeriodSBO> observationPeriods = new HashSet<ObservationPeriodSBO>();
	private Set<DeviceSBO> devices = new HashSet<DeviceSBO>();
	private Set<MeasurementSBO> measurements = new HashSet<MeasurementSBO>();

	public MockEntityManager() {
		loadFromMockDatabase();
	}

	/**
	 * Loads data from the MockDatabase if configured.
	 */
	protected void loadFromMockDatabase() {
		try {
			ResourceBundle databaseBundle = ResourceBundle
					.getBundle(RESSOURCEBUNDLE_FILE);
			String database = databaseBundle.getString(DATABASE_KEY);
			if ("mock".equals(database)) {
				MockDatabase mockDB = new MockDatabase();
				doctors.addAll(mockDB.getDoctors());
				patients.addAll(mockDB.getPatients());
				observationPeriods.addAll(mockDB.getObservationPeriods());
				devices.addAll(mockDB.getDevices());
				measurements.addAll(mockDB.getMeasurements());
			}
		} catch (MissingResourceException e) {
			// ignore >> load nothing
		}

	}

	/**
	 * Used for subclasses (i.e. test-classes).
	 */
	protected Set<DoctorSBO> getModifiableDoctors() {
		return doctors;
	}

	/**
	 * Used for subclasses (i.e. test-classes).
	 */
	protected Set<PatientSBO> getModifiablePatients() {
		return patients;
	}

	/**
	 * Used for subclasses (i.e. test-classes).
	 */
	protected Set<ObservationPeriodSBO> getModifiableObservationPeriods() {
		return observationPeriods;
	}

	/**
	 * Used for subclasses (i.e. test-classes).
	 */
	protected Set<DeviceSBO> getModifiableDevices() {
		return devices;
	}

	/**
	 * Used for subclasses (i.e. test-classes).
	 */
	protected Set<MeasurementSBO> getModifiableMeasurements() {
		return measurements;
	}

	@Override
	public DoctorSBO persistDoctor(DoctorSBO doctor) {
		doctors.add(doctor);
		return doctor;
	}

	@Override
	public DoctorSBO getDoctor(String username) {
		for (DoctorSBO doctor : doctors) {
			if (username.equals(doctor.getUsername())) {
				return doctor;
			}
		}
		return null;
	}

	@Override
	public Set<DoctorSBO> getDoctors() {
		return Collections.unmodifiableSet(doctors);
	}

	@Override
	public PatientSBO persistPatient(PatientSBO patient) {
		patients.add(patient);
		return patient;
	}

	@Override
	public Set<PatientSBO> getPatients() {
		return Collections.unmodifiableSet(patients);
	}

	@Override
	public PatientSBO findPatientById(String ssn) {
		for (PatientSBO patient : patients) {
			if (patient.getSSN().equals(ssn)) {
				return patient;
			}
		}
		return null;
	}

	@Override
	public DeviceSBO persistDevice(DeviceSBO device) {
		devices.add(device);
		return device;
	}

	@Override
	public Set<DeviceSBO> getDevices() {
		return Collections.unmodifiableSet(devices);
	}

	@Override
	public DeviceSBO findDeviceById(int id) {
		for (DeviceSBO device : devices) {
			if (device.getId() == id) {
				return device;
			}
		}
		return null;
	}

	@Override
	public ObservationPeriodSBO persistObservationPeriod(
			ObservationPeriodSBO observationPeriod) {
		if (observationPeriod.getId() == 0) {
			observationPeriod.setId(generateNextObservationPeriodId());
		}
		observationPeriods.add(observationPeriod);
		return observationPeriod;
	}

	/**
	 * Generates the next id for the observation period depending on the ids on
	 * the 'database' (generated value -> autoincrement).
	 * 
	 * @return an id for a newly persisted {@link ObservationPeriodSBO}
	 */
	private int generateNextObservationPeriodId() {
		int minId = 0;
		for (ObservationPeriodSBO period : observationPeriods) {
			minId = Math.max(minId, period.getId());
		}
		minId++;
		return minId;
	}

	@Override
	public Set<ObservationPeriodSBO> getObservationPeriods() {
		return Collections.unmodifiableSet(observationPeriods);
	}

	@Override
	public ObservationPeriodSBO findObservationPeriodById(
			int observationPeriodId) {
		for (ObservationPeriodSBO period : observationPeriods) {
			if (period.getId() == observationPeriodId) {
				return period;
			}
		}
		return null;
	}

	@Override
	public Set<ObservationPeriodSBO> findObservationPeriods(PatientSBO patient) {
		Set<ObservationPeriodSBO> returnVal = new HashSet<ObservationPeriodSBO>();
		if (patient != null) {
			for (ObservationPeriodSBO observationPeriod : observationPeriods) {
				if (patient.equals(observationPeriod.getPatient()))
					returnVal.add(observationPeriod);
			}
		}
		return returnVal;
	}

	@Override
	public MeasurementSBO persistMeasurement(MeasurementSBO measurement) {
		measurements.add(measurement);
		return measurement;
	}

	@Override
	public Set<MeasurementSBO> getMeasurements() {
		return Collections.unmodifiableSet(measurements);
	}

	@Override
	public Set<MeasurementSBO> findMeasurements(
			ObservationPeriodSBO observationPeriod) {
		Set<MeasurementSBO> returnVal = new HashSet<MeasurementSBO>();
		if (observationPeriod != null) {
			for (MeasurementSBO measurement : measurements) {
				if (observationPeriod
						.equals(measurement.getObservationPeriod()))
					returnVal.add(measurement);
			}
		}
		return returnVal;
	}

}
