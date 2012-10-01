package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

/**
 * Used if a server business object is not found in the database.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 5113532298839913094L;

	private FaultBean faultInfo;

	public EntityNotFoundException(FaultBean faultInfo) {
		super(faultInfo.getMessage());
		this.faultInfo = faultInfo;
	}

	public FaultBean getFaultInfo() {
		return this.faultInfo;
	}

}
