package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.adapter.EntityManagerAdapter;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.NotLoggedInException;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

/**
 * Integration test for Doctor login.
 * 
 * @author buergich
 */
public class DoctorLoginIntegrationTest extends AbstractIntegrationTest {

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
					DoctorSBO doctor = new DoctorSBO(USERNAME,
							PASSWORD, ACTIVATION_CODE);
					doctor.setActivated(false);
					return doctor;
				}
				return null;
			}

		};
		testServer = PatientMonitorServerTest.usingCustom(em);
		testServer.start();
	}

	/**
	 * Tests the happy-case of a doctor login.
	 */
	@Test
	public void testDoctorLogin() throws Exception {
		LoginControllerPort loginController = getLoginController();
		Session session = loginController.login(USERNAME, PASSWORD);

		assertNotNull(session);

		SessionControllerPort sessionController = getSessionController();
		boolean activated = sessionController.isActivated(session);

		assertFalse(activated);

		sessionController.enterActivationCode(ACTIVATION_CODE, session);

		assertTrue(sessionController.isActivated(session));
	}

	/**
	 * Tests if there is a {@link NotLoggedInException} thrown, if there is a
	 * wrong password.
	 */
	@Test(expected = NotLoggedInException.class)
	public void testWrongPassword() throws Exception {
		LoginControllerPort loginController = getLoginController();
		loginController.login(USERNAME, "wrong_password");
	}

	/**
	 * Tests if there is a {@link NotLoggedInException} thrown, if the doctor is
	 * not found in the database.
	 */
	@Test(expected = NotLoggedInException.class)
	public void testUsernameDoesNotExist() throws Exception {
		LoginControllerPort loginController = getLoginController();
		loginController.login("wrong_username", "password");
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
