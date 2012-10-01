package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.server.gtk.DeviceGatekeeper;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.gtk.mock.MockDeviceGatekeeper;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.adapter.EntityManagerAdapter;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.DeviceAlreadyAssignedException;
import ch.bfh.ti.sed.patmon1.ws.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.ws.IllegalStateException;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.Measurement;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

/**
 * Integration test for 'Define observation period'.
 * 
 * @author buergich
 */
public class DefineObservationPeriodIntegrationTest extends
		AbstractIntegrationTest {

	private static final String USERNAME = "mail@mail.com";
	private static final String PASSWORD = "secret";

	private PatientMonitorServer testServer;
	private MockEntityManager em;
	private TestGatekeeperFactory gkf;
	private Session session;
	private SessionControllerPort sessionController;
	private Date startDate;
	private Date endDate;

	/**
	 * Creates a mock {@link EntityManager} and starts a
	 * {@link PatientMonitorServer} and logs in.
	 */
	@Before
	public void setUp() throws Exception {
		em = new MockEntityManager();
		gkf = new TestGatekeeperFactory();
		testServer = PatientMonitorServerTest.usingCustom(em, gkf);
		testServer.start();

		// Login
		LoginControllerPort loginController = getLoginController();
		session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		sessionController = getSessionController();

		// Setup dates
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.JUNE, 10, 11, 22, 33);
		startDate = cal.getTime();
		cal.set(2012, Calendar.JULY, 20, 12, 34, 56);
		endDate = cal.getTime();
	}

	/**
	 * Tests the happy-case of a define observation period use case.
	 */
	@Test
	public void testDefineObservationPeriod() throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		em.device = persistedDevice;

		final int measurementInterval = 60;

		assertEquals(0, em.persistedObservationPeriods.size());
		assertEquals(0, gkf.gatekeepers.size());

		// Call: define
		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);

		assertEquals(1, em.persistedObservationPeriods.size());
		assertEquals(1, gkf.gatekeepers.size());
		TestDeviceGatekeeper gk = gkf.gatekeepers.get(0);
		assertEquals(1, gk.timesInitCalled);
		assertEquals(0, gk.timesTriggerMeasurementCalled);
		assertEquals(deviceId, gk.device.getId());

		// Call: test measure
		Measurement testMeasure1 = sessionController.testMeasure(session);
		assertNotNull(testMeasure1);
		assertNotNull(testMeasure1.getTimestamp());
		assertNotNull(testMeasure1.getTemperature());
		assertEquals(1, gk.timesInitCalled);
		assertEquals(1, gk.timesTriggerMeasurementCalled);

		// Call: test measure
		Measurement testMeasure2 = sessionController.testMeasure(session);
		assertNotNull(testMeasure2);
		assertNotNull(testMeasure2.getTimestamp());
		assertNotNull(testMeasure2.getTemperature());
		assertEquals(1, gk.timesInitCalled);
		assertEquals(2, gk.timesTriggerMeasurementCalled);

		// Call: end test
		sessionController.endTest(session);

		assertEquals(1, em.persistedObservationPeriods.size());
		assertEquals(1, gkf.gatekeepers.size());
		assertEquals(1, gk.timesInitCalled);
		assertEquals(2, gk.timesTriggerMeasurementCalled);
	}

	/**
	 * Tests the happy-case of a define observation period use case, when doing
	 * no tests.
	 */
	@Test
	public void testDefineObservationPeriodNoTests() throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		em.device = persistedDevice;

		final int measurementInterval = 60;

		assertEquals(0, em.persistedObservationPeriods.size());
		assertEquals(0, gkf.gatekeepers.size());

		// Call: define
		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);

		assertEquals(1, gkf.gatekeepers.size());
		TestDeviceGatekeeper gk = gkf.gatekeepers.get(0);

		// Call: end test
		sessionController.endTest(session);

		assertEquals(1, em.persistedObservationPeriods.size());
		assertEquals(1, gkf.gatekeepers.size());
		assertEquals(1, gk.timesInitCalled);
		assertEquals(0, gk.timesTriggerMeasurementCalled);
	}

	/**
	 * Tests if an IllegalStateException is thrown when testMeasure() is called
	 * after endTest().
	 */
	@Test(expected = IllegalStateException.class)
	public void testDefineObservationPeriodInvalidTestMeasure()
			throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		em.device = persistedDevice;

		final int measurementInterval = 60;

		// Call: define
		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);

		// Call: end test
		sessionController.endTest(session);

		// Call: test measure => invalid!
		sessionController.testMeasure(session);
	}

	/**
	 * Tests if an IllegalStateException is thrown when endTest() is called
	 * without defining an observation period.
	 */
	@Test(expected = IllegalStateException.class)
	public void testInvalidEndTestCall() throws Exception {
		// Call should not be allowed
		sessionController.endTest(session);
	}

	/**
	 * Tests if an Exception is thrown when testMeasure() is called without
	 * defining an observation period.
	 */
	@Test(expected = IllegalStateException.class)
	public void testInvalidTestMeasureCall() throws Exception {
		// Call should not be allowed
		sessionController.testMeasure(session);
	}

	/**
	 * Tests if an IllegalStateException is thrown when
	 * defineObservationPeriod() is called two times without calling endTest().
	 */
	@Test(expected = IllegalStateException.class)
	public void testDefineObservationPeriodTwice()
			throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		em.device = persistedDevice;

		final int measurementInterval = 60;
		final int measurementInterval2 = 40;

		// Call: define first
		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);
		
		// Call: define second
		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval2, session);
	}

	/**
	 * Tests if a DeviceAlreadyAssignedException is thrown if a device is tried
	 * to be assigned which is already assigned.
	 */
	@Test(expected = DeviceAlreadyAssignedException.class)
	public void testDeviceAlreadyAssigned() throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		ObservationPeriodSBO assignedPeriod = new ObservationPeriodSBO();

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		assignedPeriod.assignDevice(persistedDevice);
		em.device = persistedDevice;

		final int measurementInterval = 60;

		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testInvalidPatient() throws Exception {
		final String ssn = "123.45.678.900";
		// Patient is not in entity manager

		final int deviceId = 123;
		DeviceSBO persistedDevice = new DeviceSBO();
		persistedDevice.setId(deviceId);
		em.device = persistedDevice;

		final int measurementInterval = 60;

		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testInvalidDevice() throws Exception {
		final String ssn = "123.45.678.900";
		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(ssn);
		em.patient = persistedPatient;

		final int deviceId = 123;
		// Device is not in entity manager

		final int measurementInterval = 60;

		sessionController.defineObservationPeriod(ssn, deviceId, startDate,
				endDate, measurementInterval, session);
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
	 * A Mock-EntityManager which registers the persisted observation periods.
	 */
	private static class MockEntityManager extends EntityManagerAdapter {

		List<ObservationPeriodSBO> persistedObservationPeriods = new ArrayList<ObservationPeriodSBO>();
		PatientSBO patient;
		DeviceSBO device;

		@Override
		public PatientSBO findPatientById(String ssn) {
			if (patient != null && patient.getSSN().equals(ssn)) {
				return patient;
			}
			return null;
		}

		@Override
		public DeviceSBO findDeviceById(int id) {
			if (device != null && device.getId() == id) {
				return device;
			}
			return null;
		}

		@Override
		public ObservationPeriodSBO persistObservationPeriod(
				ObservationPeriodSBO observationPeriod) {
			persistedObservationPeriods.add(observationPeriod);
			return observationPeriod;
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

	/**
	 * A custom GatekeeperFactory which creates TestDeviceGatekeepers.
	 */
	private static class TestGatekeeperFactory implements GatekeeperFactory {

		List<TestDeviceGatekeeper> gatekeepers = new ArrayList<TestDeviceGatekeeper>();

		@Override
		public DeviceGatekeeper createDeviceGatekeeper(DeviceSBO device) {
			TestDeviceGatekeeper gk = new TestDeviceGatekeeper(device);
			gatekeepers.add(gk);
			return gk;
		}

	}

	/**
	 * A custom MockDeviceGatekeeper which registers the called methods.
	 */
	private static class TestDeviceGatekeeper extends MockDeviceGatekeeper {

		DeviceSBO device;
		int timesInitCalled = 0;
		int timesTriggerMeasurementCalled = 0;

		public TestDeviceGatekeeper(DeviceSBO device) {
			super(device);
			this.device = device;
		}

		@Override
		public void init(ObservationPeriodSBO period) {
			timesInitCalled++;
			super.init(period);
		}

		@Override
		public ch.bfh.ti.sed.patmon1.server.model.Measurement triggerMeasurement() {
			timesTriggerMeasurementCalled++;
			return super.triggerMeasurement();
		}

	}

}
