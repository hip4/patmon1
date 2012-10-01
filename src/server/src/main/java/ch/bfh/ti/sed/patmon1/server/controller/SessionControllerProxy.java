package ch.bfh.ti.sed.patmon1.server.controller;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ch.bfh.ti.sed.patmon1.server.controller.fault.DeviceAlreadyAssignedException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.DeviceNotAssignedException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityAlreadyExistsException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.IllegalStateException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.server.controller.impl.MeasurementList;
import ch.bfh.ti.sed.patmon1.server.controller.impl.PatientList;
import ch.bfh.ti.sed.patmon1.server.controller.impl.SessionController;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.Patient;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

/**
 * The session controller proxy is used to send authenticated requests
 * (commands) to the server. The proxy's responsibility is to make sure that
 * only request which contain a valid session are handed over to the real
 * {@link SessionController}.
 * <p>
 * It defines a {@link WebService} with JAX-WS over which the client can
 * communicate.
 */
@WebService(name = "SessionControllerPort", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
public interface SessionControllerProxy {

	/**
	 * Creates a SessionController which is stored in the SessionStorage and
	 * returns the therefore opened session.
	 * 
	 * @param doctor
	 *            the doctor to open a Sessio nfor
	 * @return the created session.
	 */
	@WebMethod(exclude = true)
	// is not published to the client
	public Session openSession(DoctorSBO doctor);

	/**
	 * Checks activation of the session's doctor.
	 * 
	 * @param session
	 *            the session token
	 * @return true, if the doctor is activated, false otherwise
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 */
	@WebMethod
	@WebResult(name = "activated")
	public boolean isActivated(@WebParam(name = "session") Session session)
			throws InvalidSessionException;

	/**
	 * Enters the activation code to activate the doctor.
	 * 
	 * @param activationCode
	 *            the activation code to enter
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 */
	@WebMethod
	public void enterActivationCode(
			@WebParam(name = "activationCode") String activationCode,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException;

	/**
	 * Logs out the doctor and disposes of the session.
	 * 
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 */
	@WebMethod
	public void logout(@WebParam(name = "session") Session session)
			throws InvalidSessionException;

	/**
	 * Registers a new patient using the patient data transfer object.
	 * 
	 * @param patient
	 *            the patient data to use for creating the patient
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityAlreadyExistsException
	 *             if there is already a Patient in the database with the ssn of
	 *             the patient in question.
	 */
	@WebMethod
	public void registerPatient(@WebParam(name = "patient") Patient patient,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityAlreadyExistsException;

	/**
	 * Returns all patients in the database for the authenticated doctor.
	 * 
	 * @param session
	 *            the session token
	 * @return a {@link PatientList} containing the patients, which is empty, if
	 *         there are no patients for the doctor.
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 */
	@WebMethod
	@WebResult(name = "patients")
	public PatientList getPatients(@WebParam(name = "session") Session session)
			throws InvalidSessionException;

	/**
	 * Defines a new observation period and sets the device into test-mode
	 * 
	 * @param patientSsn
	 *            the patient's ssn to create the observation period for
	 * @param deviceId
	 *            the device's id to initialize with the period data
	 * @param periodStart
	 *            the start of the period
	 * @param periodEnd
	 *            the end of the period
	 * @param measurementIntervalInSeconds
	 *            the interval in seconds by which measurements should be taken
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityNotFoundException
	 *             if either the patient or the device is not found in the
	 *             database
	 * @throws DeviceAlreadyAssignedException
	 *             if the device is already assigned to a observation period
	 * @throws IllegalStateException
	 *             if there is a device for the session which is still in
	 *             test-mode.
	 */
	@WebMethod
	public void defineObservationPeriod(
			@WebParam(name = "patient") String patientSsn,
			@WebParam(name = "device") Integer deviceId,
			@WebParam(name = "periodStart") Date periodStart,
			@WebParam(name = "periodEnd") Date periodEnd,
			@WebParam(name = "measurementIntervalInSeconds") int measurementIntervalInSeconds,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityNotFoundException,
			DeviceAlreadyAssignedException, IllegalStateException;

	/**
	 * Performs a test measure. This method can only be called if a device is in
	 * test-mode. The test-measurement is not stored in the database.
	 * 
	 * @param session
	 *            the session token
	 * @return the result of the test-measurement.
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws IllegalStateException
	 *             if there is no device in test-mode
	 */
	@WebMethod
	@WebResult(name = "measurement")
	public Measurement testMeasure(@WebParam(name = "session") Session session)
			throws InvalidSessionException, IllegalStateException;

	/**
	 * Ends the current test-mode on the device. There must be a device in
	 * test-mode.
	 * 
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws IllegalStateException
	 *             if there is no device in test-mode
	 */
	@WebMethod
	public void endTest(@WebParam(name = "session") Session session)
			throws InvalidSessionException, IllegalStateException;

	/**
	 * Returns all measurements in the database for the given patient (by id).
	 * The measurements can be filtered with startFilter and endFilter. There
	 * will only be measurements returned which are after startFilter and before
	 * endFilter. If either (or both) of them is null, the filter is ignored.
	 * 
	 * @param patientId
	 *            the patient to get the measurements from
	 * @param start
	 *            the startFilter
	 * @param end
	 *            the endFilter
	 * @param session
	 *            the session token
	 * @return a {@link MeasurementList} containing the measurements
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityNotFoundException
	 *             if the patient is not found in the database
	 */
	@WebMethod
	@WebResult(name = "measurements")
	public MeasurementList consultPatientMeasurements(
			@WebParam(name = "patientId") String patientId,
			@WebParam(name = "startFilter") Date start,
			@WebParam(name = "endFilter") Date end,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityNotFoundException;

	/**
	 * Returns all measurements in the database for the given period (by id).
	 * The measurements can be filtered with startFilter and endFilter. There
	 * will only be measurements returned which are after startFilter and before
	 * endFilter. If either (or both) of them is null, the filter is ignored.
	 * 
	 * @param periodId
	 *            the period to get the measurements from
	 * @param start
	 *            the startFilter
	 * @param end
	 *            the endFilter
	 * @param session
	 *            the session token
	 * @return a {@link MeasurementList} containing the measurements
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityNotFoundException
	 *             if the patient is not found in the database
	 */
	@WebResult(name = "measurements")
	public MeasurementList consultObservationPeriodMeasurements(
			@WebParam(name = "observationPeriodId") Integer periodId,
			@WebParam(name = "startFilter") Date start,
			@WebParam(name = "endFilter") Date end,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityNotFoundException;

	/**
	 * Returns a device from an observation period. After this, the device can
	 * be reused and assigned to an other patient.
	 * 
	 * @param deviceId
	 *            the device to return
	 * @param session
	 *            the session token
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityNotFoundException
	 *             if the device is not found in the database
	 * @throws DeviceNotAssignedException
	 *             if the device is not assigned to an observation period
	 */
	@WebMethod
	public void returnDevice(@WebParam(name = "deviceId") Integer deviceId,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityNotFoundException,
			DeviceNotAssignedException;

}