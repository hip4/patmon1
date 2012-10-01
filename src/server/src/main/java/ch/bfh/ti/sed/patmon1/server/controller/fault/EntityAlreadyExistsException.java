package ch.bfh.ti.sed.patmon1.server.controller.fault;

import javax.xml.ws.WebFault;

/**
 * Used if the client tries to persist an entity of which there is already an
 * entity in the database with the given primary key.
 */
@WebFault(faultBean = "ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean")
public class EntityAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 2357669282837269608L;

	private FaultBean faultInfo;

	public EntityAlreadyExistsException(FaultBean faultInfo) {
		super(faultInfo.getMessage());
		this.faultInfo = faultInfo;
	}

	public FaultBean getFaultInfo() {
		return this.faultInfo;
	}

}
