package ch.bfh.ti.sed.patmon1.server.model.sbo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.Doctor;
import ch.bfh.ti.sed.patmon1.server.model.Patient;

public class PatientSBO extends Patient {

	private DoctorSBO treatingDoctor;

	private Set<ObservationPeriodSBO> observationPeriods = new HashSet<ObservationPeriodSBO>();

	/**
	 * Static factory method for creating a PatientSBO from a Patient
	 * 
	 * @param patient
	 * @return
	 */
	public static PatientSBO forPatient(Patient patient) {
		PatientSBO sbo = new PatientSBO();
		sbo.setName(patient.getName());
		sbo.setSSN(patient.getSSN());
		return sbo;
	}

	/**
	 * returns the doctor who is treating the patient
	 * @return treating doctor
	 */
	public Doctor getTreatingDoctor() {
		return treatingDoctor;
	}

	/**
	 * sets the patient which is treating this patient and
	 * adds or removes him from the patients treating list
	 * @param treatingDoctor
	 */
	public void setTreatingDoctor(DoctorSBO treatingDoctor) {
		if (this.treatingDoctor != null) {
			this.treatingDoctor.removePatient(this);
		}

		if (treatingDoctor == null) {
			this.treatingDoctor = null;
		} else {
			treatingDoctor.addPatient(this);
			this.treatingDoctor = treatingDoctor;
		}
	}

	/**
	 * add the given observation period to
	 * the patient
	 * @param period
	 * @return true if successful
	 */
	boolean addObservationPeriod(ObservationPeriodSBO period) {
		if (observationPeriods.contains(period)) {
			return false;
		}
		this.observationPeriods.add(period);
		return true;
	}

	/**
	 * removes the given observation period from 
	 * the patient
	 * @param period
	 * @return true if successful
	 */
	boolean removeObservationPeriod(ObservationPeriodSBO period) {
		if (observationPeriods.contains(period)) {
			this.observationPeriods.remove(period);
			return true;
		}
		return false;
	}
	
	/**
	 * return all observationperiods concerning the given patient
	 * 
	 * @return unmodifiable set of observation periods
	 */
	public Set<ObservationPeriodSBO> getObservationPeriods(){
		return Collections.unmodifiableSet(observationPeriods);
	}

}
