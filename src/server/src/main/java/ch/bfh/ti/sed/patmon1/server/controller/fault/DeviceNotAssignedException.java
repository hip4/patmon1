package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceNotAssignedBusinessException;

/**
 * Used if a device should be returned, which is not assigned.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class DeviceNotAssignedException extends Exception {

	private static final long serialVersionUID = 2793651987460373716L;

	private FaultBean faultInfo;

	public DeviceNotAssignedException(DeviceNotAssignedBusinessException cause) {
		super(cause.getMessage());
		this.faultInfo = new FaultBean();
		this.faultInfo.setMessage(cause.getMessage());
	}

}
