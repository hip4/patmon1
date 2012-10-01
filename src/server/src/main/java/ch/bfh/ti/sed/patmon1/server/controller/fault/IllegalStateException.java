package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

/**
 * Used if a request is invalid because the server is in a specific state which
 * does not allow the request to be made at the moment.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class IllegalStateException extends Exception {

	private static final long serialVersionUID = 2357669282837269608L;

	private FaultBean faultInfo;

	public IllegalStateException(FaultBean faultInfo) {
		super(faultInfo.getMessage());
		this.faultInfo = faultInfo;
	}

	public FaultBean getFaultInfo() {
		return this.faultInfo;
	}

}
