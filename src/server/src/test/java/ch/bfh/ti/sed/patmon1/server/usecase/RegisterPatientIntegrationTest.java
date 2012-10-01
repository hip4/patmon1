package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.adapter.EntityManagerAdapter;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.Patient;
import ch.bfh.ti.sed.patmon1.ws.EntityAlreadyExistsException;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

/**
 * Integration test for Doctor login.
 * 
 * @author buergich
 */
public class RegisterPatientIntegrationTest extends AbstractIntegrationTest {

	private static final String USERNAME = "mail@mail.com";
	private static final String PASSWORD = "secret";

	private PatientMonitorServer testServer;
	private EntityManager em;

	/**
	 * Creates a mock {@link EntityManager} and starts a
	 * {@link PatientMonitorServer}.
	 */
	@Before
	public void setUp() {
		em = new EntityManagerAdapter() {

			private Set<PatientSBO> patients = new HashSet<PatientSBO>();

			@Override
			public DoctorSBO getDoctor(String username) {
				if (username.equals(USERNAME)) {
					DoctorSBO doctor = new DoctorSBO(USERNAME, PASSWORD);
					doctor.setActivated(true);
					return doctor;
				}
				return null;
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

		};
		testServer = PatientMonitorServerTest.usingCustom(em);
		testServer.start();
	}

	/**
	 * Tests the happy-case of a register patient use case.
	 */
	@Test
	public void testDoctorLogin() throws Exception {
		// Login
		LoginControllerPort loginController = getLoginController();
		Session session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		SessionControllerPort sessionController = getSessionController();
		boolean activated = sessionController.isActivated(session);
		assertTrue(activated);

		Patient patient = new Patient();
		final String name = "hans meier";
		patient.setName(name);
		final String ssn = "123.45.678.900";
		patient.setSSN(ssn);

		List<Patient> serverPatients = sessionController.getPatients(session)
				.getPatients();
		assertEquals(0, serverPatients.size());

		sessionController.registerPatient(patient, session);

		serverPatients = sessionController.getPatients(session).getPatients();
		assertEquals(1, serverPatients.size());
		Patient retrievedPatient = serverPatients.get(0);
		assertEquals(name, retrievedPatient.getName());
		assertEquals(ssn, retrievedPatient.getSSN());
	}

	/**
	 * Tests if a EntityAlreadyExistsException is thrown, if the client tries
	 * to register a patient which already exists
	 */
	@Test(expected = EntityAlreadyExistsException.class)
	public void testDoctorLoginPatientAlreadyExists() throws Exception {
		// Login
		LoginControllerPort loginController = getLoginController();
		Session session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		SessionControllerPort sessionController = getSessionController();
		boolean activated = sessionController.isActivated(session);
		assertTrue(activated);

		Patient patient = new Patient();
		final String name = "hans meier";
		patient.setName(name);
		final String ssn = "123.45.678.900";
		patient.setSSN(ssn);

		PatientSBO alreadyRegisteredPatient = new PatientSBO();
		alreadyRegisteredPatient.setSSN(ssn);
		em.persistPatient(alreadyRegisteredPatient);

		sessionController.registerPatient(patient, session);
	}

	/**
	 * Helper method to create a {@link LoginControllerPort}.
	 * 
	 * @return a {@link LoginControllerPort}
	 */
	private LoginControllerPort getLoginController() {
		LoginControllerService service = new LoginControllerService();
		return service.getLoginControllerPort();
	}

	/**
	 * Helper method to create a {@link SessionControllerPort}.
	 * 
	 * @return a {@link SessionControllerPort}
	 */
	private SessionControllerPort getSessionController() {
		SessionControllerService service = new SessionControllerService();
		return service.getSessionControllerPort();
	}

	/**
	 * Stops the test-server.
	 */
	@After
	public void tearDown() {
		testServer.stop();
	}

}
