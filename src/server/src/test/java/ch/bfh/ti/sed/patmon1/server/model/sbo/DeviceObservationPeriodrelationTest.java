package ch.bfh.ti.sed.patmon1.server.model.sbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DeviceObservationPeriodrelationTest {

	private ObservationPeriodSBO observationPeriod;
	private DeviceSBO device;

	@Before
	public void setUp() {
		device = new DeviceSBO();
		observationPeriod = new ObservationPeriodSBO();
	}

	@Test
	public void testEmptyRelation() {
		assertTrue("device should not be set",
				observationPeriod.getDevice() == null);
		assertTrue("observationperiod should no be set",
				device.getCurrentlyAssignedObservationPeriod() == null);
	}

	@Test
	public void testAddRemoveMeasurement() {
		assertTrue("device should not be set",
				observationPeriod.getDevice() == null);
		assertTrue("observationperiod should no be set",
				device.getCurrentlyAssignedObservationPeriod() == null);

		// add device to observationperiod
		observationPeriod.assignDevice(device);

		assertEquals("device is not set on the observation period", device,
				observationPeriod.getDevice());
		assertEquals("observation period is not set to the device",
				observationPeriod,
				device.getCurrentlyAssignedObservationPeriod());
		
		assertTrue(device.isCurrentlyAssigned());
		
		
		// remove the measurement from the observationperiod
		observationPeriod.returnDevice();

		assertEquals("device should not be set", device,
				observationPeriod.getDevice());
		assertTrue("observationperiod should no be set",
				device.getCurrentlyAssignedObservationPeriod() == null);


		assertFalse(device.isCurrentlyAssigned());
		
	}
	
	@Test (expected = IllegalStateException.class)
	public void testWrongReturnmentOfDevice(){
		// device was not set to the observationperiod
		// should return a exception
		observationPeriod.returnDevice();
	}
	
	

	@Test(expected = IllegalStateException.class)
	public void testWrongReturnmentOfDevice2() {
		assertTrue("device should not be set",
				observationPeriod.getDevice() == null);
		assertTrue("observationperiod should no be set",
				device.getCurrentlyAssignedObservationPeriod() == null);

		// add device to observationperiod
		observationPeriod.assignDevice(device);

		assertEquals("device is not set on the observation period", device,
				observationPeriod.getDevice());
		assertEquals("observation period is not set to the device",
				observationPeriod,
				device.getCurrentlyAssignedObservationPeriod());

		// wrong call to return the device
		observationPeriod.assignDevice(null);
	}

}
