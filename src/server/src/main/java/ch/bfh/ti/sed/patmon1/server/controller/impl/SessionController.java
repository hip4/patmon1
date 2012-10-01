package ch.bfh.ti.sed.patmon1.server.controller.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy;
import ch.bfh.ti.sed.patmon1.server.controller.fault.DeviceNotAssignedException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityAlreadyExistsException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean;
import ch.bfh.ti.sed.patmon1.server.controller.fault.IllegalStateException;
import ch.bfh.ti.sed.patmon1.server.domain.DeviceReturnalBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.MeasurementBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.ObservationPeriodDefinitionBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceAlreadyAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceNotAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.Patient;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

/**
 * Session controller which is created for each open session and holds the
 * doctor.
 */
public class SessionController {

	private ApplicationContext context;

	private DoctorSBO doctor;

	private ObservationPeriodDefinitionBusinessHandler currentObservationHandler = null;

	/**
	 * Creates a new session controller for the given doctor. Requires the
	 * application's context.
	 * 
	 * @param context
	 *            the application's context
	 * @param doctor
	 *            the doctor which opened the session
	 */
	SessionController(ApplicationContext context, DoctorSBO doctor) {
		this.context = context;
		this.doctor = doctor;
	}

	/**
	 * @return the session's doctor.
	 */
	DoctorSBO getDoctor() {
		return doctor;
	}

	/**
	 * @return true if the session's doctor is activated, false otherwise
	 * @see SessionControllerProxy#isActivated(ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public boolean isActivated() {
		return doctor.isActivated();
	}

	/**
	 * Tries to activate the session's doctor with the given activation code. If
	 * activation fails, isActivated() still returns false.
	 * 
	 * @param activationCode
	 *            the activation code to use
	 * @see SessionControllerProxy#enterActivationCode(String,
	 *      ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public void activate(String activationCode) {
		doctor.activate(activationCode);
	}

	/**
	 * Registers a patient.
	 * 
	 * @param patient
	 *            the patient to register
	 * @throws EntityAlreadyExistsException
	 *             if the patient already exists
	 * @see SessionControllerProxy#registerPatient(Patient,
	 *      ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public void registerPatient(Patient patient)
			throws EntityAlreadyExistsException {
		if (patientExists(patient.getSSN())) {
			FaultBean faultInfo = new FaultBean();
			faultInfo.setMessage("Patient already exists!");
			throw new EntityAlreadyExistsException(faultInfo);
		} else {
			PatientSBO sboPatient = PatientSBO.forPatient(patient);
			sboPatient.setTreatingDoctor(doctor);
			context.getEntityManager().persistPatient(sboPatient);
		}
	}

	/**
	 * Checks if a patient exist.
	 * 
	 * @param ssn
	 *            the patients ssn (id)
	 * @return true, if the patient exists, false otherwise
	 */
	private boolean patientExists(String ssn) {
		Set<PatientSBO> patients = context.getEntityManager().getPatients();
		for (Patient p : patients) {
			if (p.getSSN().equals(ssn)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns all patients for the doctor in the database.
	 * 
	 * @return a Set of all doctor's patients
	 * @see SessionControllerProxy#getPatients(ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public Set<Patient> getPatients() {
		Set<PatientSBO> patients = context.getEntityManager().getPatients();
		Set<Patient> clientPatients = new HashSet<Patient>();
		for (PatientSBO patient : patients) {
			if (patient.getTreatingDoctor().equals(doctor)) {
				clientPatients.add(patient);
			}
		}
		return clientPatients;
	}

	/**
	 * Defines an observation period for the given patient.
	 * 
	 * @see SessionControllerProxy#defineObservationPeriod(String, Integer,
	 *      Date, Date, int, ch.bfh.ti.sed.patmon1.server.controller.Session)
	 * @throws EntityNotFoundException
	 *             if the patient is not found in the database
	 * @throws DeviceAlreadyAssignedBusinessException
	 *             if the device is already assigned
	 * @throws IllegalStateException
	 *             if there is a device still in test-mode
	 * @see SessionControllerProxy#defineObservationPeriod(String, Integer,
	 *      Date, Date, int, ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public void defineObservationPeriod(String patientSsn, int deviceId,
			Date periodStart, Date periodEnd, int measurementIntervalInSeconds)
			throws EntityNotFoundException,
			DeviceAlreadyAssignedBusinessException, IllegalStateException {
		if (currentObservationHandler != null) {
			throw new IllegalStateException(new FaultBean(
					"Still in test-mode, Call endTest() first!"));
		}
		PatientSBO patient = loadRequiredPatientById(patientSsn);
		DeviceSBO device = loadRequiredDeviceById(deviceId);
		ObservationPeriodDefinitionBusinessHandler observationHandler = context
				.getBusinessHandlerFactory().createObservationHandler(patient,
						device);
		observationHandler.createAndInitializeObservationPeriod(periodStart,
				periodEnd, measurementIntervalInSeconds);
		this.currentObservationHandler = observationHandler;
	}

	/**
	 * Perfoms a test measure on the device in test-mode.
	 * 
	 * @return the result of the test-measurement.
	 * @throws IllegalStateException
	 *             if there is no device in test-mode
	 * @see SessionControllerProxy#testMeasure(ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public Measurement testMeasure() throws IllegalStateException {
		if (currentObservationHandler == null) {
			throw new IllegalStateException(new FaultBean(
					"No device in test mode"));
		}
		return currentObservationHandler.testMeasure();
	}

	/**
	 * Sets the device in test-mode into productive mode and ends the test-phase
	 * 
	 * @throws IllegalStateException
	 *             if there is no device in test-mode
	 * @see SessionControllerProxy#endTest(ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public void endTest() throws IllegalStateException {
		if (currentObservationHandler == null) {
			throw new IllegalStateException(new FaultBean(
					"No device in test mode"));
		}
		currentObservationHandler = null;
	}

	/**
	 * Returns measurements for the given patient or period. Either patientId or
	 * periodId have to be null!
	 * 
	 * @return a Set of measurements which were found for the given parameters
	 * @throws EntityNotFoundException
	 *             if periodId and patientId are not found in the database
	 * @see SessionControllerProxy#consultObservationPeriodMeasurements(Integer,
	 *      Date, Date, ch.bfh.ti.sed.patmon1.server.controller.Session)
	 * @see SessionControllerProxy#consultPatientMeasurements(String, Date,
	 *      Date, ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public Set<? extends Measurement> consultMeasurements(String patientId,
			Integer periodId, Date start, Date end)
			throws EntityNotFoundException {
		MeasurementBusinessHandler handler;
		if (periodId == null) {
			PatientSBO patient = loadRequiredPatientById(patientId);
			handler = context.getBusinessHandlerFactory()
					.createMeasurementHandler(patient);
		} else {
			ObservationPeriodSBO period = loadRequiredObservationPeriodById(periodId
					.intValue());
			handler = context.getBusinessHandlerFactory()
					.createMeasurementHandler(period);
		}
		return handler.getMeasurements(start, end);
	}

	/**
	 * Loads the PatientSBO from the database.
	 * 
	 * @throws EntityNotFoundException
	 *             if the patient is not found.
	 */
	private PatientSBO loadRequiredPatientById(String ssn)
			throws EntityNotFoundException {
		PatientSBO patient = context.getEntityManager().findPatientById(ssn);
		if (patient == null) {
			throw new EntityNotFoundException(new FaultBean(
					"Invalid patient id. Patient not found in database."));
		}
		return patient;
	}

	/**
	 * Loads the DeviceSBO from the database.
	 * 
	 * @throws EntityNotFoundException
	 *             if the device is not found
	 */
	private DeviceSBO loadRequiredDeviceById(int deviceId)
			throws EntityNotFoundException {
		DeviceSBO device = context.getEntityManager().findDeviceById(deviceId);
		if (device == null) {
			throw new EntityNotFoundException(new FaultBean(
					"Invalid device id. Device not found in database."));
		}
		return device;
	}

	/**
	 * Loads the ObservationPeriodSBO from the database.
	 * 
	 * @throws EntityNotFoundException
	 *             if the observation period is not found
	 */
	private ObservationPeriodSBO loadRequiredObservationPeriodById(
			int observationPeriodId) throws EntityNotFoundException {
		ObservationPeriodSBO period = context.getEntityManager()
				.findObservationPeriodById(observationPeriodId);
		if (period == null) {
			throw new EntityNotFoundException(
					new FaultBean(
							"Invalid observation period id. ObservationPeriod not found in database."));
		}
		return period;
	}

	/**
	 * Returns the given device.
	 * 
	 * @param deviceId
	 *            the id of the device to 'return'.
	 * @throws EntityNotFoundException
	 *             if the device is not found in the database
	 * @throws DeviceNotAssignedException
	 *             if the device is not assigned to an observation period.
	 * @see SessionControllerProxy#returnDevice(Integer,
	 *      ch.bfh.ti.sed.patmon1.server.controller.Session)
	 */
	public void returnDevice(int deviceId) throws EntityNotFoundException,
			DeviceNotAssignedException {
		DeviceSBO deviceSBO = loadRequiredDeviceById(deviceId);
		DeviceReturnalBusinessHandler returnalHandler = context
				.getBusinessHandlerFactory().createDeviceReturnalHandler(
						deviceSBO);
		try {
			returnalHandler.returnDevice();
		} catch (DeviceNotAssignedBusinessException e) {
			throw new DeviceNotAssignedException(e);
		}
	}

}
