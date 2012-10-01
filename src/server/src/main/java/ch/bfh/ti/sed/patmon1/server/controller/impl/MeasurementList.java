package ch.bfh.ti.sed.patmon1.server.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ch.bfh.ti.sed.patmon1.server.model.Measurement;

/**
 * Type for sending a List of Measurements with JAX-WS to the client. Not that Sets
 * are not supported and therefore a List is sent.
 */
@XmlRootElement
public class MeasurementList {

	private List<Measurement> measurements = new ArrayList<Measurement>();

	/**
	 * Contains the list encapsulated in this class.
	 * 
	 * @return the results
	 */
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	/**
	 * Sets the Measurement list on this container class.
	 * 
	 * @param results
	 *            the results to set
	 */
	public void setMeasurements(List<Measurement> results) {
		this.measurements = results;
	}

}
