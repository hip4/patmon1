package ch.bfh.ti.sed.patmon1.server.model.sbo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.Doctor;

public class DoctorSBO extends Doctor {

	private Set<PatientSBO> patients = new HashSet<PatientSBO>();

	/**
	 * @param email
	 *            is equal to the doctors username
	 * @param password
	 */
	public DoctorSBO(String email, String password) {
		setUsername(email);
		setPassword(password);
	}

	public DoctorSBO(String email, String password, String activationCode) {
		this(email, password);
		setActivationCode(activationCode);
	}

	
	/**
	 * Returns a list of all patients, who are treated by the given doctor
	 * 
	 * @return Set of patients
	 */
	public Set<PatientSBO> getPatients(){
		return Collections.unmodifiableSet(patients);
	}
	
	
	/**
	 * compares the given password with the stored password
	 * 
	 * @param password
	 * @return equality fulfilled, exception: false if given and stored password
	 *         are both null
	 */
	public boolean comparePassword(String password) {
		if (getPassword() != null)
			return getPassword().equals(password);

		return false;
	}

	/**
	 * Activates the Doctor with the given activationCode. The Doctor is only
	 * activated, if the correct activation code is entered.
	 * 
	 * @param activationCode
	 *            the activation code to validate
	 * @return true, if the activation was successful, false otherwise
	 */
	public boolean activate(String activationCode) {
		if (getActivationCode() != null
				&& getActivationCode().equals(activationCode)) {
			setActivated(true);
		}
		return isActivated();
	}

	/**
	 * References a doctor with its patient
	 * @param patient 
	 * @return if the assignment was successful
	 */
	boolean addPatient(PatientSBO patient) {
		if (patients.contains(patient)) {
			return false;
		}
		this.patients.add(patient);
		return true;
	}
	
	/**
	 * Dereferences a doctor with its patient
	 * @param patient to be removed
	 * @return if the deassignment was successful
	 */
	boolean removePatient(PatientSBO patient) {
		if (patients.contains(patient)) {
			this.patients.remove(patient);
			return true;
		}
		return false;
	}
}
