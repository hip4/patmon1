package ch.bfh.ti.sed.patmon1.server.gtk.mock;

import ch.bfh.ti.sed.patmon1.server.gtk.DeviceGatekeeper;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;

/**
 * Implementation for the {@link GatekeeperFactory}.
 */
public class GatekeeperFactoryImpl implements GatekeeperFactory {

	@Override
	public DeviceGatekeeper createDeviceGatekeeper(DeviceSBO device) {
		return new MockDeviceGatekeeper(device);
	}

}
