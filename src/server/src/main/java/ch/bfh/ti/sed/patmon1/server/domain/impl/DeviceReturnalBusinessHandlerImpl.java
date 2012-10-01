package ch.bfh.ti.sed.patmon1.server.domain.impl;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.DeviceReturnalBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceNotAssignedBusinessException;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;

public class DeviceReturnalBusinessHandlerImpl extends
		AbstractContextAwarableBusinessHandler implements
		DeviceReturnalBusinessHandler {

	private DeviceSBO device;

	DeviceReturnalBusinessHandlerImpl(ApplicationContext context,
			DeviceSBO device) {
		super(context);
		this.device = device;
	}

	@Override
	public void returnDevice() throws DeviceNotAssignedBusinessException {
		if (!device.isCurrentlyAssigned()) {
			throw new DeviceNotAssignedBusinessException();
		} 
		ObservationPeriodSBO period = device.getCurrentlyAssignedObservationPeriod();
		period.returnDevice();
	}

}
