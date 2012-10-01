package ch.bfh.ti.sed.patmon1.server.controller.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.controller.Session;
import ch.bfh.ti.sed.patmon1.server.controller.SessionStorage;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

public class SessionStorageImpl implements SessionStorage {

	private Map<Session, SessionController> activeSessions = new HashMap<Session, SessionController>();
	private ApplicationContext context;

	public SessionStorageImpl(ApplicationContext context) {
		this.context = context;
	}

	/**
	 * @return an unmodifiable map of the active session.
	 */
	Map<Session, SessionController> getActiveSessions() {
		return Collections.unmodifiableMap(activeSessions);
	}

	@Override
	public Session createAndRegisterSession(DoctorSBO doctor) {
		Session session = new Session();
		session.initialize();
		activeSessions.put(session, new SessionController(context, doctor));
		return session;
	}

	@Override
	public void dropSession(Session session) {
		activeSessions.remove(session);
	}

	@Override
	public SessionController validateAndGetSessionController(Session session) {
		return activeSessions.get(session);
	}
}
