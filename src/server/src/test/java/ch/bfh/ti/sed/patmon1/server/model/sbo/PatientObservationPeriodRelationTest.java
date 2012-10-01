package ch.bfh.ti.sed.patmon1.server.model.sbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PatientObservationPeriodRelationTest {

	private ObservationPeriodSBO observationPeriod;
	private PatientSBO patient;

	@Before
	public void setUp() {
		observationPeriod = new ObservationPeriodSBO();
		observationPeriod.setId(1);
		observationPeriod.setStart(new Date());
		observationPeriod.setEnd(new Date());
		patient = new PatientSBO();
		patient.setSSN("111-222-333");
		patient.setName("TestPatint");
	}

	@Test
	public void testEmptyRelation() {
		assertTrue("there should not be a patient set on observationperiod",
				observationPeriod.getPatient() == null);
		assertTrue("the patients observationperiods should be initialized",
				patient.getObservationPeriods() != null);
		assertTrue("there should be no observationperiod set on the patient",
				patient.getObservationPeriods().size() == 0);
	}

	@Test
	public void testAddRemoveObservationPeriod() {
		assertTrue("there should not be a patient set on observationperiod",
				observationPeriod.getPatient() == null);
		assertTrue("the patients observationperiods should be initialized",
				patient.getObservationPeriods() != null);
		assertTrue("there should be no observationperiod set on the patient",
				patient.getObservationPeriods().size() == 0);

		// set the patient
		observationPeriod.setPatient(patient);
		
		assertEquals(1,patient.getObservationPeriods().size());
		assertEquals(observationPeriod,patient.getObservationPeriods().iterator().next());
		
		assertEquals("the observationperiod was not assigned the right patients",patient, observationPeriod.getPatient());
		
		// remove the patient
		
		observationPeriod.setPatient(null);
		
		assertTrue("there should not be a patient set on observationperiod",
				observationPeriod.getPatient() == null);
		assertTrue("there should be no observationperiod set on the patient",
				patient.getObservationPeriods().size() == 0);
		
	}
}
