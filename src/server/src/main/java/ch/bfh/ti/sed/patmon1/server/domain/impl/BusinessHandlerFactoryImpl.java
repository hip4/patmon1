package ch.bfh.ti.sed.patmon1.server.domain.impl;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.BusinessHandlerFactory;
import ch.bfh.ti.sed.patmon1.server.domain.DeviceReturnalBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.MeasurementBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.domain.ObservationPeriodDefinitionBusinessHandler;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DeviceSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.ObservationPeriodSBO;
import ch.bfh.ti.sed.patmon1.server.model.sbo.PatientSBO;

public class BusinessHandlerFactoryImpl implements BusinessHandlerFactory {

	private ApplicationContext context;

	public BusinessHandlerFactoryImpl(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public ObservationPeriodDefinitionBusinessHandler createObservationHandler(
			PatientSBO patient, DeviceSBO device) {
		return new ObservationBusinessHandlerImpl(context, patient, device);
	}

	@Override
	public MeasurementBusinessHandler createMeasurementHandler(
			PatientSBO patient) {
		return new MeasurementPatientBusinessHandler(context, patient);
	}

	@Override
	public MeasurementBusinessHandler createMeasurementHandler(
			ObservationPeriodSBO period) {
		return new MeasurementObservationPeriodBusinessHandler(context, period);
	}

	@Override
	public DeviceReturnalBusinessHandler createDeviceReturnalHandler(
			DeviceSBO device) {
		return new DeviceReturnalBusinessHandlerImpl(context, device);
	}

}
