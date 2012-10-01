package ch.bfh.ti.sed.patmon1.server.persistence.mock;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.model.Patient;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

public class EntityManagerTest {
	private EntityManager entityManager;

	@Before
	public void setUp() {
		entityManager = new MockEntityManager();
	}

	@Test
	public void testPersistPatient() {
		final String patientname = "Rolf";
		final String patientid = "123-456-789";
		PatientSBO patient = new PatientSBO() {

			@Override
			public void setName(String name) {
				// TODO Auto-generated method stub

			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return patientname;
			}

			@Override
			public String getSSN() {
				// TODO Auto-generated method stub
				return patientid;
			}

			@Override
			public void setSSN(String ssn) {
				throw new UnsupportedOperationException();
			}
		};

		Patient genPatient = entityManager.persistPatient(patient);

		assertTrue("new patient is not in the EntityManager", entityManager
				.getPatients().contains(genPatient));
	}

}
