package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

/**
 * Used if a login fails.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class NotLoggedInException extends Exception {

	private static final long serialVersionUID = 1197992139036995891L;

	private FaultBean faultInfo;

	public NotLoggedInException(FaultBean faultInfo) {
		super(faultInfo.getMessage());
		this.faultInfo = faultInfo;
	}

	public FaultBean getFaultInfo() {
		return this.faultInfo;
	}

}
