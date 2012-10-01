package ch.bfh.ti.sed.patmon1.server.model.sbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ObservationPeriodMeasurementTest {

	private ObservationPeriodSBO observationPeriod;
	private MeasurementSBO measurement;

	@Before
	public void setUp() {
		observationPeriod = new ObservationPeriodSBO();
		observationPeriod.setId(1);
		observationPeriod.setMeasurementInterval(10);

		measurement = new MeasurementSBO();
		measurement.setTemperature(new BigDecimal(35.2));
		measurement.setTimestamp(new Date());
	}

	@Test
	public void testEmptyRelation() {
		assertTrue("set of measurements should be initialied",
				observationPeriod.getMeasurements() != null);
		assertEquals("set of measurements should be empty", 0,
				observationPeriod.getMeasurements().size());
		assertTrue("observationperiod should no be set",
				measurement.getObservationPeriod() == null);
	}

	@Test
	public void testAddRemoveMeasurement() {
		assertTrue("set of measurements should be initialied",
				observationPeriod.getMeasurements() != null);
		assertEquals("set of measurements should be empty", 0,
				observationPeriod.getMeasurements().size());
		assertTrue("observationperiod should not be set",
				measurement.getObservationPeriod() == null);

		// add the measurement to the observationperiod
		measurement.setObservationPeriod(observationPeriod);

		assertEquals("set of measurements", 1, observationPeriod
				.getMeasurements().size());
		assertNotNull(measurement.getObservationPeriod());
		assertEquals(observationPeriod, measurement.getObservationPeriod());

		// remove the measurement from the observationperiod
		measurement.setObservationPeriod(null);

		assertEquals("set of measurements should be empty", 0,
				observationPeriod.getMeasurements().size());
		assertTrue("observationperiod should no be set",
				measurement.getObservationPeriod() == null);

	}

}
