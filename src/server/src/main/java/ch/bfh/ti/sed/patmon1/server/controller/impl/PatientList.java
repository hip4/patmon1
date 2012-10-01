package ch.bfh.ti.sed.patmon1.server.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ch.bfh.ti.sed.patmon1.server.model.Patient;

/**
 * Type for sending a List of Patients with JAX-WS to the client. Not that Sets
 * are not supported and therefore a List is sent.
 */
@XmlRootElement
public class PatientList {

	private List<Patient> patients = new ArrayList<Patient>();

	/**
	 * Contains the list encapsulated in this class.
	 * 
	 * @return the results
	 */
	public List<Patient> getPatients() {
		return patients;
	}

	/**
	 * Sets the Patient list on this container class.
	 * 
	 * @param results
	 *            the results to set
	 */
	public void setPatients(List<Patient> results) {
		this.patients = results;
	}

}
