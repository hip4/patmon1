package ch.bfh.ti.sed.patmon1.server.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebService;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.controller.Session;
import ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy;
import ch.bfh.ti.sed.patmon1.server.controller.SessionStorage;
import ch.bfh.ti.sed.patmon1.server.controller.fault.DeviceAlreadyAssignedException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.DeviceNotAssignedException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityAlreadyExistsException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean;
import ch.bfh.ti.sed.patmon1.server.controller.fault.IllegalStateException;
import ch.bfh.ti.sed.patmon1.server.controller.fault.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceAlreadyAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.Patient;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

/**
 * Implementation for {@link SessionControllerProxy}.
 */
@WebService(serviceName = "SessionControllerService", portName = "SessionControllerPort", endpointInterface = "ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
public class SessionControllerProxyImpl implements SessionControllerProxy {

	private SessionStorage sessionStorage;

	public SessionControllerProxyImpl(ApplicationContext context) {
		sessionStorage = new SessionStorageImpl(context);
	}

	/**
	 * Checks if the given session is valid and returns the session controller
	 * for it.
	 * 
	 * @param session
	 *            the session to check
	 * @return the session controller for the session
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 */
	private SessionController checkValidSession(Session session)
			throws InvalidSessionException {
		SessionController controller = sessionStorage
				.validateAndGetSessionController(session);
		if (controller == null) {
			FaultBean faultInfo = new FaultBean();
			faultInfo.setMessage("Session is invalid!");
			throw new InvalidSessionException(faultInfo);
		}
		return controller;
	}

	@Override
	public Session openSession(DoctorSBO doctor) {
		return sessionStorage.createAndRegisterSession(doctor);
	}

	@Override
	public boolean isActivated(@WebParam(name = "session") Session session)
			throws InvalidSessionException {
		SessionController controller = checkValidSession(session);
		return controller.isActivated();
	}

	@Override
	public void enterActivationCode(
			@WebParam(name = "activationCode") String activationCode,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException {
		SessionController controller = checkValidSession(session);
		controller.activate(activationCode);
	}

	@Override
	public void logout(@WebParam(name = "session") Session session)
			throws InvalidSessionException {
		checkValidSession(session);
		sessionStorage.dropSession(session);
	}

	@Override
	public void registerPatient(@WebParam(name = "patient") Patient patient,
			@WebParam(name = "session") Session session)
			throws InvalidSessionException, EntityAlreadyExistsException {
		SessionController controller = checkValidSession(session);
		controller.registerPatient(patient);
	}

	@Override
	public PatientList getPatients(@WebParam(name = "session") Session session)
			throws InvalidSessionException {
		SessionController controller = checkValidSession(session);

		// We use a custom data transfer object since jax-ws does not support
		// java collections
		PatientList patients = new PatientList();
		patients.setPatients(new ArrayList<Patient>(controller.getPatients()));
		return patients;
	}

	@Override
	public void defineObservationPeriod(String patientSsn, Integer deviceId,
			Date periodStart, Date periodEnd, int measurementIntervalInSeconds,
			Session session) throws InvalidSessionException,
			EntityNotFoundException, DeviceAlreadyAssignedException,
			IllegalStateException {
		SessionController controller = checkValidSession(session);
		try {
			controller.defineObservationPeriod(patientSsn, deviceId,
					periodStart, periodEnd, measurementIntervalInSeconds);
		} catch (DeviceAlreadyAssignedBusinessException e) {
			throw new DeviceAlreadyAssignedException(e);
		}
	}

	@Override
	public Measurement testMeasure(Session session)
			throws InvalidSessionException, IllegalStateException {
		SessionController controller = checkValidSession(session);
		return controller.testMeasure();
	}

	@Override
	public void endTest(Session session) throws InvalidSessionException,
			IllegalStateException {
		SessionController controller = checkValidSession(session);
		controller.endTest();
	}

	@Override
	public MeasurementList consultPatientMeasurements(String patientId,
			Date start, Date end, Session session)
			throws InvalidSessionException, EntityNotFoundException {
		return consultMeasurements(patientId, null, start, end, session);
	}

	@Override
	public MeasurementList consultObservationPeriodMeasurements(
			Integer periodId, Date start, Date end, Session session)
			throws InvalidSessionException, EntityNotFoundException {
		return consultMeasurements(null, periodId, start, end, session);
	}

	/**
	 * Implementation of consultMeasurements(), which requires either a
	 * patientId or a periodId.
	 * 
	 * @throws InvalidSessionException
	 *             if the session is invalid
	 * @throws EntityNotFoundException
	 *             if the patientId or the periodId returned no valid SBO from
	 *             the database.
	 * @see SessionControllerProxy#consultObservationPeriodMeasurements(Integer,
	 *      Date, Date, Session)
	 * @see SessionControllerProxy#consultPatientMeasurements(String, Date,
	 *      Date, Session)
	 */
	private MeasurementList consultMeasurements(String patientId,
			Integer periodId, Date start, Date end, Session session)
			throws InvalidSessionException, EntityNotFoundException {
		if (!((patientId == null) ^ (periodId == null))) {
			throw new IllegalArgumentException(
					"Either patientId or periodId have to be null (XOR)!");
		}
		SessionController controller = checkValidSession(session);
		Set<? extends Measurement> measurements = controller
				.consultMeasurements(patientId, periodId, start, end);
		MeasurementList result = new MeasurementList();
		result.setMeasurements(new ArrayList<Measurement>(measurements));
		return result;
	}

	@Override
	public void returnDevice(Integer deviceId, Session session)
			throws InvalidSessionException, EntityNotFoundException,
			DeviceNotAssignedException {
		SessionController controller = checkValidSession(session);
		controller.returnDevice(deviceId);
	}

}
