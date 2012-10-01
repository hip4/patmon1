package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.adapter.EntityManagerAdapter;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

/**
 * Integration test for Doctor logout.
 * 
 * @author buergich
 */
public class DoctorLogoutIntegrationTest extends AbstractIntegrationTest {

	private static final String USERNAME = "mail@mail.com";
	private static final String PASSWORD = "secret";
	private static final String ACTIVATION_CODE = "activationCode";

	private PatientMonitorServer testServer;

	/**
	 * Creates a mock {@link EntityManager} and starts a
	 * {@link PatientMonitorServer}.
	 */
	@Before
	public void setUp() {
		EntityManager em = new EntityManagerAdapter() {

			@Override
			public DoctorSBO getDoctor(String username) {
				if (username.equals(USERNAME)) {
					DoctorSBO doctor = new DoctorSBO(USERNAME, PASSWORD,
							ACTIVATION_CODE);
					doctor.setActivated(true);
					return doctor;
				}
				return null;
			}

		};
		testServer = PatientMonitorServerTest.usingCustom(em);
		testServer.start();
	}

	/**
	 * Tests the happy-case of a doctor logout.
	 */
	@Test(expected = InvalidSessionException.class)
	public void testDoctorLogin() throws Exception {
		// Login
		LoginControllerPort loginController = getLoginController();
		Session session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		SessionControllerPort sessionController = getSessionController();
		try {
			boolean activated = sessionController.isActivated(session);
			assertTrue(activated);

			// Doctor is logged in!

			sessionController.logout(session);
		} catch (InvalidSessionException e) {
			fail();
		}

		// Should return a InvalidSessionException since we are not logged in
		// any more
		sessionController.isActivated(session);
	}

	/**
	 * Tests if there is a {@link InvalidSessionException} thrown, if the there
	 * is no valid Session.
	 */
	@Test(expected = InvalidSessionException.class)
	public void testWrongPassword() throws Exception {
		SessionControllerPort sessionController = getSessionController();
		Session session = new Session();

		// Should not be allowed and throw a NotLoggedInException
		sessionController.logout(session);
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
