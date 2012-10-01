package ch.bfh.ti.sed.patmon1.server.gtk.mock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import ch.bfh.ti.sed.patmon1.server.gtk.DeviceGatekeeper;
import ch.bfh.ti.sed.patmon1.server.model.Measurement;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;

/**
 * A mock implementation for the {@link DeviceGatekeeper}.
 */
public class MockDeviceGatekeeper implements DeviceGatekeeper {
	
	protected MockDeviceGatekeeper(DeviceSBO device) {
		// should store the device
	}

	@Override
	public void init(ObservationPeriodSBO period) {
		// nothing to do...
	}

	@Override
	public Measurement triggerMeasurement() {
		Measurement measurement = new Measurement();

		Date now = new Date();
		measurement.setTimestamp(now);
		measurement.setTemperature(createRandomTemparatureMeasurement());

		return measurement;
	}

	/**
	 * Creates a random temperature measurement.
	 * 
	 * @return a random BigDecimal between 35.00 and 40.99
	 */
	private BigDecimal createRandomTemparatureMeasurement() {
		Random random = new Random();
		int degree = random.nextInt(6) + 35;
		int hundreth = random.nextInt(100);
		BigDecimal temperature = new BigDecimal(degree + "." + hundreth);

		return temperature;
	}

}
