package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

import ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy;

/**
 * Used if there is no valid session for a request to the
 * {@link SessionControllerProxy}.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class InvalidSessionException extends Exception {

	private static final long serialVersionUID = -2513071039003060278L;

	private FaultBean faultInfo;

	public InvalidSessionException(FaultBean faultInfo) {
		super(faultInfo.getMessage());
		this.faultInfo = faultInfo;
	}

	public FaultBean getFaultInfo() {
		return this.faultInfo;
	}

}
