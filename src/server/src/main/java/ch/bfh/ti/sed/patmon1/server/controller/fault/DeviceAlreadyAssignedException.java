package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

import ch.bfh.ti.sed.patmon1.server.domain.exception.DeviceAlreadyAssignedBusinessException;

/**
 * Used if a device is tried to be assigned to an observation period which is
 * already assigned.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class DeviceAlreadyAssignedException extends Exception {

	private static final long serialVersionUID = 7259899172818149646L;

	private FaultBean faultInfo;

	public DeviceAlreadyAssignedException(
			DeviceAlreadyAssignedBusinessException cause) {
		super(cause.getMessage());
		this.faultInfo = new FaultBean();
		this.faultInfo.setMessage(cause.getMessage());
	}

}
