package ch.bfh.ti.sed.patmon1.server.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ch.bfh.ti.sed.patmon1.server.controller.fault.NotLoggedInException;

/**
 * The login controller is used to login into the system. There is only one
 * login controller in the server.
 * <p>
 * It defines a {@link WebService} with JAX-WS over which the client can
 * communicate.
 */
@WebService(name = "LoginControllerPort", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
public interface LoginController {

	/**
	 * Login of the doctor. If the credentials are correct the method returns a
	 * Session(ControllerProxy)-Object which should be used for further
	 * communication with the server
	 * 
	 * @param username
	 *            username or email of the doctor
	 * @param password
	 *            password of the doctor
	 * @return a SessionControllerProxy-object or null if the credentials are
	 *         not correct
	 * @throws NotLoggedInException
	 */
	@WebMethod
	@WebResult(name = "session")
	public Session login(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws NotLoggedInException;

}