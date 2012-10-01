package ch.bfh.ti.sed.patmon1.server.domain;

import java.util.Date;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;

/**
 * Handler for Measurements which is typed to a Patient or a ObservationPeriod.
 */
public interface MeasurementBusinessHandler {

	/**
	 * Returns a Set of measurements filtered by Date.
	 * 
	 * @param filterFrom
	 *            filter argument to filter Measurements which are before this
	 *            date, ignored if null
	 * @param filterTo
	 *            filter argument to filter Measurements which are after this
	 *            date, ignored if null
	 * @return all Measurements which are between filterFrom and filterTo, if
	 *         one of them (or both) is null, the filter is ignored
	 */
	public Set<MeasurementSBO> getMeasurements(Date filterFrom, Date filterTo);

}
