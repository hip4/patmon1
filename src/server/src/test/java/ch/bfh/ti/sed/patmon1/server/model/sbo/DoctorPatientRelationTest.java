package ch.bfh.ti.sed.patmon1.server.model.sbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DoctorPatientRelationTest {
	private DoctorSBO doctor;
	private PatientSBO patient;

	@Before
	public void setUp() {
		doctor = new DoctorSBO("test@mail.ch", "12345");
		patient = new PatientSBO();
		patient.setSSN("111-222-333");
		patient.setName("TestPatint");
	}

	@Test
	public void testEmptyRelation() {
		Set<PatientSBO> patients = doctor.getPatients();
		// checks if an empty set of patients is initilized
		assertTrue("set of patients should be initialized", patients != null);
		// asserts that the list is empty
		assertEquals("set should be empty", 0, patients.size());
	}

	@Test
	public void testAddPatient() {
		// calls the patient to add himself to the doctors patient list
		patient.setTreatingDoctor(doctor);
		// asserts that the doctor's patient list contains the patient
		assertEquals(1, doctor.getPatients().size());
		assertEquals(doctor, patient.getTreatingDoctor());
		PatientSBO doctorsPatient = doctor.getPatients().iterator().next();
		assertEquals(patient, doctorsPatient);

	}
	
	
	@Test
	public void testRemovePatient() {
		// calls the patient to add himself to the doctors patient list
		patient.setTreatingDoctor(doctor);
		assertEquals(1, doctor.getPatients().size());
		// calls the patient to remove himself to the doctors patient list
		patient.setTreatingDoctor(null);
		assertEquals(0, doctor.getPatients().size());
		assertEquals(null, patient.getTreatingDoctor());
	}

}
