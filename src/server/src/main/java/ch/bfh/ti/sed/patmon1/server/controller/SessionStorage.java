package ch.bfh.ti.sed.patmon1.server.controller;

import ch.bfh.ti.sed.patmon1.server.controller.impl.SessionController;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

/**
 * A storage for the open sessions which is used application-wide.
 */
public interface SessionStorage {

	/**
	 * Creates a Session and a SessionController for the given doctor.
	 * 
	 * @param doctor
	 *            the doctor to open a session for
	 * @return the created and initialized session
	 */
	public Session createAndRegisterSession(DoctorSBO doctor);

	/**
	 * Drops a session from the storage.
	 * 
	 * @param session
	 *            the session to drop
	 */
	public void dropSession(Session session);

	/**
	 * Validates a session token and returns the corresponding session
	 * controller.
	 * 
	 * @param session
	 *            the session to validate
	 * @return the assigned session controller, returns null, if the session is
	 *         invalid
	 */
	public SessionController validateAndGetSessionController(Session session);

}