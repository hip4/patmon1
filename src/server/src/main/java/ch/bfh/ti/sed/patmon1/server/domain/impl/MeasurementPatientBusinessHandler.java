package ch.bfh.ti.sed.patmon1.server.domain.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.MeasurementBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.model.sbo.MeasurementSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

public class MeasurementPatientBusinessHandler extends
		AbstractContextAwarableBusinessHandler implements
		MeasurementBusinessHandler {

	private PatientSBO patient;

	MeasurementPatientBusinessHandler(ApplicationContext context,
			PatientSBO patient) {
		super(context);
		this.patient = patient;
	}

	@Override
	public Set<MeasurementSBO> getMeasurements(Date filterFrom, Date filterTo) {
		Set<MeasurementSBO> results = new HashSet<MeasurementSBO>();
		Set<ObservationPeriodSBO> periods = getApplicationContext()
				.getEntityManager().findObservationPeriods(patient);
		for (ObservationPeriodSBO period : periods) {
			MeasurementObservationPeriodBusinessHandler observationHandler = new MeasurementObservationPeriodBusinessHandler(
					getApplicationContext(), period);
			Set<MeasurementSBO> periodMeasurements = observationHandler
					.getMeasurements(filterFrom, filterTo);
			results.addAll(periodMeasurements);
		}
		return results;
	}

}
