package ch.bfh.ti.sed.patmon1.server.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.PatientMonitorServer;
import ch.bfh.ti.sed.patmon1.server.PatientMonitorServerTest;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.usecase.adapter.EntityManagerAdapter;
import ch.bfh.ti.sed.patmon1.server.usecase.integration.AbstractIntegrationTest;
import ch.bfh.ti.sed.patmon1.ws.EntityNotFoundException;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.Measurement;
import ch.bfh.ti.sed.patmon1.ws.MeasurementList;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

/**
 * Integration test for 'Consult measurement period'.
 * 
 * @author buergich
 */
public class ConsultMeasurementIntegrationTest extends AbstractIntegrationTest {

	private static final String USERNAME = "mail@mail.com";
	private static final String PASSWORD = "secret";

	private static final String PATIENT_SSN = "123.45.678.900";
	private static final int PERIOD1_ID = 1;
	private static final int PERIOD2_ID = 2;

	private PatientMonitorServer testServer;
	private MockEntityManager em;
	private Session session;
	private SessionControllerPort sessionController;

	private ObservationPeriodSBO period1 = new ObservationPeriodSBO();
	private ObservationPeriodSBO period2 = new ObservationPeriodSBO();

	private MeasurementSBO measurement11 = new MeasurementSBO();
	private MeasurementSBO measurement12 = new MeasurementSBO();

	private MeasurementSBO measurement21 = new MeasurementSBO();
	private MeasurementSBO measurement22 = new MeasurementSBO();

	/**
	 * Creates a mock {@link EntityManager} and starts a
	 * {@link PatientMonitorServer} and logs in.
	 */
	@Before
	public void setUp() throws Exception {
		em = new MockEntityManager();
		testServer = PatientMonitorServerTest.usingCustom(em);
		testServer.start();

		// Login
		LoginControllerPort loginController = getLoginController();
		session = loginController.login(USERNAME, PASSWORD);
		// Get session controller
		sessionController = getSessionController();

		PatientSBO persistedPatient = new PatientSBO();
		persistedPatient.setSSN(PATIENT_SSN);
		em.patient = persistedPatient;

		period1.setPatient(persistedPatient);
		period1.setId(PERIOD1_ID);
		period2.setPatient(persistedPatient);
		period2.setId(PERIOD2_ID);
		Set<ObservationPeriodSBO> periods = new HashSet<ObservationPeriodSBO>();
		periods.add(period1);
		periods.add(period2);
		em.persistedObservationPeriods = periods;

		Calendar cal = GregorianCalendar.getInstance();

		// Period 1, January 1st
		measurement11.setObservationPeriod(period1);
		cal.set(2012, Calendar.JANUARY, 1);
		measurement11.setTimestamp(cal.getTime());

		// Period 1, March 1st
		measurement12.setObservationPeriod(period1);
		cal.set(2012, Calendar.MARCH, 1);
		measurement12.setTimestamp(cal.getTime());

		// Period 2, February 1st
		measurement21.setObservationPeriod(period2);
		cal.set(2012, Calendar.FEBRUARY, 1);
		measurement21.setTimestamp(cal.getTime());

		// Period 2, April 1st
		measurement22.setObservationPeriod(period2);
		cal.set(2012, Calendar.APRIL, 1);
		measurement22.setTimestamp(cal.getTime());

		Set<MeasurementSBO> measurements = new HashSet<MeasurementSBO>();
		Collections.addAll(measurements, measurement11, measurement12,
				measurement21, measurement22);
		em.persistedMeasurements = measurements;
	}

	/**
	 * Tests the happy-case of a consult measurement use case (with
	 * ObservationPeriod).
	 */
	@Test
	public void testConsultMeasurementsByObservationPeriod() throws Exception {
		MeasurementList measurementList = sessionController
				.consultObservationPeriodMeasurements(PERIOD1_ID, null, null,
						session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(2, measurements.size());

		assertTrue(containsMeasurement(measurements, measurement11));
		assertTrue(containsMeasurement(measurements, measurement12));

		assertFalse(containsMeasurement(measurements, measurement21));
		assertFalse(containsMeasurement(measurements, measurement22));
	}

	/**
	 * Tests the happy-case of a consult measurement use case (with Patient).
	 */
	@Test
	public void testConsultMeasurementsByPatient() throws Exception {
		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, null, null, session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(4, measurements.size());

		assertTrue(containsMeasurement(measurements, measurement11));
		assertTrue(containsMeasurement(measurements, measurement12));
		assertTrue(containsMeasurement(measurements, measurement21));
		assertTrue(containsMeasurement(measurements, measurement22));
	}

	/**
	 * Tests if the correct Measurements are returned if the start filter is
	 * set.
	 */
	@Test
	public void testConsultMeasurementsStartFilter() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.FEBRUARY, 15);
		Date startFilter = cal.getTime();

		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, startFilter, null,
						session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(2, measurements.size());

		assertTrue(containsMeasurement(measurements, measurement12));
		assertTrue(containsMeasurement(measurements, measurement22));

		assertFalse(containsMeasurement(measurements, measurement11));
		assertFalse(containsMeasurement(measurements, measurement21));
	}

	/**
	 * Tests if the correct Measurements are returned if the end filter is set.
	 */
	@Test
	public void testConsultMeasurementsEndFilter() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.FEBRUARY, 15);
		Date endFilter = cal.getTime();

		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, null, endFilter,
						session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(2, measurements.size());

		assertTrue(containsMeasurement(measurements, measurement11));
		assertTrue(containsMeasurement(measurements, measurement21));

		assertFalse(containsMeasurement(measurements, measurement12));
		assertFalse(containsMeasurement(measurements, measurement22));
	}

	/**
	 * Tests if the correct Measurements are returned if both filters are set.
	 */
	@Test
	public void testConsultMeasurementsBothFilter() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.JANUARY, 15);
		Date startFilter = cal.getTime();
		cal.set(2012, Calendar.MARCH, 15);
		Date endFilter = cal.getTime();

		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, startFilter,
						endFilter, session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(2, measurements.size());

		assertTrue(containsMeasurement(measurements, measurement12));
		assertTrue(containsMeasurement(measurements, measurement21));

		assertFalse(containsMeasurement(measurements, measurement11));
		assertFalse(containsMeasurement(measurements, measurement22));
	}

	/**
	 * Tests if no measurements are returned if the filter dates are too narrow.
	 */
	@Test
	public void testConsultMeasurementsNarrowFilter() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.JANUARY, 15);
		Date startFilter = cal.getTime();
		cal.set(2012, Calendar.JANUARY, 16);
		Date endFilter = cal.getTime();

		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, startFilter,
						endFilter, session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(0, measurements.size());
	}

	/**
	 * Tests if no measurements are returned if the filter dates are overlapping
	 * narrow (and especially no Exception is thrown).
	 */
	@Test
	public void testConsultMeasurementsOverlappingFilter() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2012, Calendar.APRIL, 15);
		Date startFilter = cal.getTime();
		cal.set(2011, Calendar.DECEMBER, 15);
		Date endFilter = cal.getTime();

		MeasurementList measurementList = sessionController
				.consultPatientMeasurements(PATIENT_SSN, startFilter,
						endFilter, session);
		List<Measurement> measurements = measurementList.getMeasurements();

		assertEquals(0, measurements.size());
	}

	/**
	 * Helper method for this test class which compares a MeasurementSBO's
	 * timestamp with the timestamps in the Measurement Collection.
	 */
	private boolean containsMeasurement(Collection<Measurement> measurements,
			MeasurementSBO measurement) {
		boolean containsMeasurement = false;
		for (Measurement m : measurements) {
			if (m.getTimestamp().equals(measurement.getTimestamp())) {
				containsMeasurement = true;
			}
		}
		return containsMeasurement;
	}

	/**
	 * Tests if an {@link EntityNotFoundException} is thrown if an invalid
	 * patient is sent.
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testConsultMeasurementsInvalidPatient() throws Exception {
		sessionController.consultPatientMeasurements("invalid patient", null,
				null, session);
	}

	/**
	 * Tests if an {@link EntityNotFoundException} is thrown if an invalid
	 * observation period is sent.
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testConsultMeasurementsInvalidObservationPeriod()
			throws Exception {
		sessionController.consultObservationPeriodMeasurements(-123, null,
				null, session);
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

		Set<MeasurementSBO> persistedMeasurements = new HashSet<MeasurementSBO>();
		Set<ObservationPeriodSBO> persistedObservationPeriods = new HashSet<ObservationPeriodSBO>();
		PatientSBO patient;

		@Override
		public PatientSBO findPatientById(String ssn) {
			if (patient != null && patient.getSSN().equals(ssn)) {
				return patient;
			}
			return null;
		}

		@Override
		public ObservationPeriodSBO findObservationPeriodById(
				int observationPeriodId) {
			for (ObservationPeriodSBO period : persistedObservationPeriods) {
				if (period.getId() == observationPeriodId) {
					return period;
				}
			}
			return null;
		}

		@Override
		public Set<ObservationPeriodSBO> findObservationPeriods(
				PatientSBO patient) {
			Set<ObservationPeriodSBO> returnVal = new HashSet<ObservationPeriodSBO>();
			if (patient != null) {
				for (ObservationPeriodSBO observationPeriod : persistedObservationPeriods) {
					if (patient.equals(observationPeriod.getPatient()))
						returnVal.add(observationPeriod);
				}
			}
			return returnVal;
		}

		@Override
		public Set<MeasurementSBO> findMeasurements(
				ObservationPeriodSBO observationPeriod) {
			Set<MeasurementSBO> returnVal = new HashSet<MeasurementSBO>();
			if (observationPeriod != null) {
				for (MeasurementSBO measurement : persistedMeasurements) {
					if (observationPeriod.equals(measurement
							.getObservationPeriod()))
						returnVal.add(measurement);
				}
			}
			return returnVal;
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
