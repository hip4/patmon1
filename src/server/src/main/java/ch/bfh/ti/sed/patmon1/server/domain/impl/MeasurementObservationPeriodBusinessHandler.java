package ch.bfh.ti.sed.patmon1.server.domain.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.MeasurementBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;

public class MeasurementObservationPeriodBusinessHandler extends
		AbstractContextAwarableBusinessHandler implements
		MeasurementBusinessHandler {

	private ObservationPeriodSBO period;
	
	MeasurementObservationPeriodBusinessHandler(ApplicationContext context,
			ObservationPeriodSBO period) {
		super(context);
		this.period = period;
	}

	@Override
	public Set<MeasurementSBO> getMeasurements(Date filterFrom, Date filterTo) {
		Set<MeasurementSBO> result = new HashSet<MeasurementSBO>();
		Set<MeasurementSBO> measurements = getApplicationContext().getEntityManager().findMeasurements(period);
		for (MeasurementSBO measurement : measurements) {
			if (filterFrom != null && measurement.getTimestamp().before(filterFrom)) {
				continue;
			} else if (filterTo != null && measurement.getTimestamp().after(filterTo)) {
				continue;
			}
			result.add(measurement);
		}
		return result;
	}

}
