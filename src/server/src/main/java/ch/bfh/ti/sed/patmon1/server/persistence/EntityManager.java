package ch.bfh.ti.sed.patmon1.server.persistence;

import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

/**
 * The system's entity manager.
 */
public interface EntityManager {

	// Doctor

	/**
	 * Persists a Doctor in the database.
	 * 
	 * @param doctor
	 *            the doctor to persist
	 * @return the persisted doctor object
	 */
	public DoctorSBO persistDoctor(DoctorSBO doctor);

	/**
	 * Returns a Set of all Doctors in the database.
	 * 
	 * @return Unmodifiable set of all stored doctors
	 */
	public Set<DoctorSBO> getDoctors();

	/**
	 * Returns the doctor sbo for the given username.
	 * 
	 * @param username
	 * @return the doctor sbo, null, if not found
	 */
	public DoctorSBO getDoctor(String username);

	// Patient

	/**
	 * Persists a patient in the database.
	 * 
	 * @param patient
	 *            a patient
	 * @return A reference of the patient which is stored in the EntityManager
	 */
	public PatientSBO persistPatient(PatientSBO patient);

	/**
	 * @return all patients in the database.
	 */
	public Set<PatientSBO> getPatients();

	/**
	 * Returns the Patient from the database with the given id (social security
	 * number).
	 * 
	 * @param ssn
	 *            the patient's id (social security number)
	 * @return if found the Patient from the database, null otherwise
	 */
	public PatientSBO findPatientById(String ssn);

	// Device

	/**
	 * Persists a device in the database.
	 * 
	 * @param device
	 * @return a reference of the persisted device
	 */
	public DeviceSBO persistDevice(DeviceSBO device);

	/**
	 * @return all devices in the database
	 */
	public Set<DeviceSBO> getDevices();

	/**
	 * Returns the Device from the database with the given id (serial number).
	 * 
	 * @param id
	 *            the device's id (serial number)
	 * @return if found the Device from the database, null otherwise
	 */
	public DeviceSBO findDeviceById(int id);

	// ObservationPeriod

	/**
	 * Persists an observation period in the database
	 * 
	 * @param observationPeriod
	 * @return the persisted observation period.
	 */
	public ObservationPeriodSBO persistObservationPeriod(
			ObservationPeriodSBO observationPeriod);

	/**
	 * @return all observation periods in the database
	 */
	public Set<ObservationPeriodSBO> getObservationPeriods();

	/**
	 * Returns the observation period for the given id.
	 * 
	 * @param observationPeriodId
	 * @return the observation period sbo if found, null otherwise
	 */
	public ObservationPeriodSBO findObservationPeriodById(
			int observationPeriodId);

	/**
	 * Returns a set of all observation periods for the given patient.
	 * 
	 * @param patient
	 * @return the found observation periods
	 */
	public Set<ObservationPeriodSBO> findObservationPeriods(PatientSBO patient);

	// Measurement

	/**
	 * Persists a measurement in the database.
	 * 
	 * @param measurement
	 * @return the persisted measurement
	 */
	public MeasurementSBO persistMeasurement(MeasurementSBO measurement);

	/**
	 * @return all measurements in the database
	 */
	public Set<MeasurementSBO> getMeasurements();

	/**
	 * Returns all measurements for the given observation period.
	 * 
	 * @param observationPeriod
	 * @return a set of observation periods, which is empty if none if found
	 */
	public Set<MeasurementSBO> findMeasurements(
			ObservationPeriodSBO observationPeriod);

}