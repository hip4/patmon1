package ch.bfh.ti.sed.patmon1.server.controller.impl;

import javax.jws.WebService;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.controller.LoginController;
import ch.bfh.ti.sed.patmon1.server.controller.Session;
import ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy;
import ch.bfh.ti.sed.patmon1.server.controller.fault.FaultBean;
import ch.bfh.ti.sed.patmon1.server.controller.fault.NotLoggedInException;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

/**
 * Implementation for {@link LoginController}.
 * 
 * @see LoginController
 */
@WebService(serviceName = "LoginControllerService", portName = "LoginControllerPort", endpointInterface = "ch.bfh.ti.sed.patmon1.server.controller.LoginController", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
public class LoginControllerImpl implements LoginController {

	private final ApplicationContext context;
	private final SessionControllerProxy sessionController;

	/**
	 * Creates a LoginController, which opens a session controller on the
	 * SessionControllerProxy on a successful login.
	 * 
	 * @param context
	 *            the system's application context
	 * @param sessionController
	 *            the system's session controller
	 */
	public LoginControllerImpl(ApplicationContext context,
			SessionControllerProxy sessionController) {
		this.context = context;
		this.sessionController = sessionController;
	}

	@Override
	public Session login(String username, String password)
			throws NotLoggedInException {
		DoctorSBO doctor = context.getEntityManager().getDoctor(username);
		if (doctor == null) {
			FaultBean fault = new FaultBean();
			fault.setMessage("User not found");
			throw new NotLoggedInException(fault);
		}
		boolean validPassword = doctor.comparePassword(password);

		if (validPassword) {
			Session session = sessionController.openSession(doctor);
			return session;
		}

		FaultBean fault = new FaultBean();
		fault.setMessage("Wrong password");
		throw new NotLoggedInException(fault);
	}
}
