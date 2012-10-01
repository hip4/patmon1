package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.persistence.mock.MockEntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.DeviceNotAssignedException;
import ch.bfh.ti.sed.patmon1.ws.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.NotLoggedInException;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

public class DeviceReturnIntegrationTest extends AbstractIntegrationTest {
	private static final String USERNAME = "mail@mail.com";
	private static final String PASSWORD = "secret";

	private static final String PATIENT_SSN = "123.456.789.0";
	private static final int DEVICE_ID = 1;

	private PatientMonitorServer testServer;
	private SessionControllerPort sessionController;
	private Session session;
	private TestMockEntityManager em;

	/**
	 * Creates a mock {@link EntityManager} and starts a
	 * {@link PatientMonitorServer}.
	 */
	@Before
	public void setUp() throws NotLoggedInException {
		em = new TestMockEntityManager();
		testServer = PatientMonitorServerTest.usingCustom(em);
		testServer.start();

		// Login
		LoginControllerPort loginController = getLoginController();
		session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		sessionController = getSessionController();
	}

	/**
	 * Happy case test for the 'return device' use case.
	 */
	@Test
	public void testReturnDevice() throws DeviceNotAssignedException,
			EntityNotFoundException, InvalidSessionException {
		assertEquals(1, em.getDevices().size());
		DeviceSBO device = em.getDevices().iterator().next();
		assertTrue(device.isCurrentlyAssigned());

		sessionController.returnDevice(new Integer(DEVICE_ID), session);

		assertFalse(device.isCurrentlyAssigned());
	}

	/**
	 * Tests if an {@link EntityNotFoundException} is thrown if an invalid
	 * deviceId is sent.
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testReturnDeviceInvalidDeviceId()
			throws DeviceNotAssignedException, EntityNotFoundException,
			InvalidSessionException {
		sessionController.returnDevice(new Integer(-123), session);
	}

	/**
	 * Tests if an {@link DeviceNotAssignedException} is thrown if device is
	 * returned which is not assigned.
	 */
	@Test(expected = DeviceNotAssignedException.class)
	public void testReturnDeviceNotAssigned()
			throws DeviceNotAssignedException, EntityNotFoundException,
			InvalidSessionException {
		assertEquals(1, em.getDevices().size());
		DeviceSBO device = em.getDevices().iterator().next();
		// Already return the device
		device.getCurrentlyAssignedObservationPeriod().returnDevice();

		sessionController.returnDevice(new Integer(DEVICE_ID), session);
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

	/**
	 * A Mock-EntityManager for this test class.
	 */
	private static class TestMockEntityManager extends MockEntityManager {

		@Override
		protected void loadFromMockDatabase() {
			PatientSBO patient = new PatientSBO();
			patient.setSSN(PATIENT_SSN);
			getModifiablePatients().add(patient);

			ObservationPeriodSBO period = new ObservationPeriodSBO();
			DeviceSBO device = new DeviceSBO();
			device.setId(DEVICE_ID);
			period.assignDevice(device);
			getModifiableObservationPeriods().add(period);
			getModifiableDevices().add(device);
		}

		@Override
		public DoctorSBO getDoctor(String username) {
			// used for login..
			if (username.equals(USERNAME)) {
				DoctorSBO doctor = new DoctorSBO(USERNAME, PASSWORD);
				doctor.setActivated(true);
				return doctor;
			}
			return null;
		}

	}

}
