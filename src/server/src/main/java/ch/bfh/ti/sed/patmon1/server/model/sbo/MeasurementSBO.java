package ch.bfh.ti.sed.patmon1.server.model.sbo;

import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.ObservationPeriod;

/**
 * Server business object for Measurement.
 * <p>
 * Note: relation to Device is not yet implemented
 */
public class MeasurementSBO extends Measurement {

	private ObservationPeriodSBO observationPeriod;

	/**
	 * @return related observationperiod
	 */
	public ObservationPeriod getObservationPeriod() {
		return observationPeriod;
	}

	/**
	 * Sets the relation between the {@link MeasurementSBO} and the
	 * {@link ObservationPeriodSBO}. The {@link ObservationPeriodSBO} will be
	 * updated to.
	 * 
	 * @param observationPeriod
	 *            the observation period to set
	 */
	public void setObservationPeriod(ObservationPeriodSBO observationPeriod) {
		if (this.observationPeriod != null) {
			this.observationPeriod.removeMeasurement(this);
		}
		if(observationPeriod != null){
			observationPeriod.addMeasurement(this);
		}
		
		this.observationPeriod = observationPeriod;
	}
	
}
