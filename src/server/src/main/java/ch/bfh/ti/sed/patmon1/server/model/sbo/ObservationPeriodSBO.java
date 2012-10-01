package ch.bfh.ti.sed.patmon1.server.model.sbo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.ObservationPeriod;

public class ObservationPeriodSBO extends ObservationPeriod {
	private PatientSBO patient;
	private DeviceSBO dataSourceDevice;
	private Set<MeasurementSBO> measurements = new HashSet<MeasurementSBO>();

	/**
	 * query method to retrieve the concerned patient
	 * 
	 * @return Business Object of the concerned patient
	 */
	public PatientSBO getPatient() {
		return this.patient;
	}

	/**
	 * References the observationperiod with the patient
	 * 
	 * @param patient
	 *            to be referenced
	 */
	public void setPatient(PatientSBO patient) {
		if (this.patient != null) {
			this.patient.removeObservationPeriod(this);
		}
		if (patient != null) {
			patient.addObservationPeriod(this);
		}
		this.patient = patient;
	}

	/**
	 * query method to retrieve the device which is monitoring the patients data
	 * 
	 * @return
	 */
	public DeviceSBO getDevice() {
		return dataSourceDevice;
	}

	/**
	 * used to set the device which will do the monitoring
	 * 
	 * @param device
	 */
	public void assignDevice(DeviceSBO device) {
		if (device == null)
			throw new IllegalStateException();

		if (device.isCurrentlyAssigned()) {
			throw new IllegalArgumentException("Device is already in use");
		}
		device.setCurrentlyAssignedObservationPeriod(this);

		this.dataSourceDevice = device;
	}

	/**
	 * return the device after the monitoring was done ( dereferences the device
	 * from the obervation period but not visa-versa)
	 */
	public void returnDevice() {
		if (dataSourceDevice == null) {
			throw new IllegalStateException("No device assigned yet");
		} else if (!this.equals(dataSourceDevice
				.getCurrentlyAssignedObservationPeriod())) {
			throw new IllegalStateException("Device is not assigned");
		}
		dataSourceDevice.returnDevice(this);
	}

	/** 
	 * Add the given measurement to the observation period
	 * @param measurement
	 * @return if successful
	 */
	boolean addMeasurement(MeasurementSBO measurement) {
		if (measurements.contains(measurement)) {
			return false;
		}
		this.measurements.add(measurement);
		return true;
	}

	
	/** 
	 * remove the given measurement from the observation period
	 * @param measurement
	 * @return true if successful
	 */
	boolean removeMeasurement(MeasurementSBO measurement) {
		if (measurements.contains(measurement)) {
			this.measurements.remove(measurement);
			return true;
		}
		return false;
	}

	/**
	 * return the measurements which are related to this observation period
	 * 
	 * @return set of the related measurements or an empty set
	 */
	public Set<MeasurementSBO> getMeasurements() {
		return Collections.unmodifiableSet(measurements);
	}
}
