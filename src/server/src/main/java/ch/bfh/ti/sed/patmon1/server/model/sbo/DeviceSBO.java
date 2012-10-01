package ch.bfh.ti.sed.patmon1.server.model.sbo;

import ch.bfh.ti.sed.patmon1.server.model.Device;

/**
 * Server business object for Device.
 * <p>
 * Note: relation to Measurement is not yet implemented
 */
public class DeviceSBO extends Device {

	private ObservationPeriodSBO currentlyAssignedObservationPeriod = null;

	/**
	 * @return the currently assigned observation period, null if none.
	 */
	public ObservationPeriodSBO getCurrentlyAssignedObservationPeriod() {
		return currentlyAssignedObservationPeriod;
	}

	/**
	 * Sets the {@link ObservationPeriodSBO} to which the Device is currently
	 * assigned to. This relation is only one-way, the state of the relation is
	 * only reflected on the device.
	 * 
	 * @param period
	 *            the period to assign the device to
	 */
	void setCurrentlyAssignedObservationPeriod(ObservationPeriodSBO period) {
		this.currentlyAssignedObservationPeriod = period;
	}

	/**
	 * @return true, if the device is currently assigned to an observation
	 *         period, false otherwise
	 */
	public boolean isCurrentlyAssigned() {
		return (currentlyAssignedObservationPeriod != null);
	}

	/**
	 * Returns the device. This method should only be called by the assigned
	 * ObservationPeriodSBO.
	 */
	void returnDevice(ObservationPeriodSBO period) {
		if (isCurrentlyAssigned()
				&& currentlyAssignedObservationPeriod.equals(period)) {
			currentlyAssignedObservationPeriod = null;
		} else {
			throw new IllegalArgumentException(
					"returnDevice() should only be called by the "
							+ "corresponding ObservationPeriodSBO");
		}
	}
	
}
