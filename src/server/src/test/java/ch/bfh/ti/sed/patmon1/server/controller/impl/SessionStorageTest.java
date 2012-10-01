package ch.bfh.ti.sed.patmon1.server.controller.impl;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.context.impl.ApplicationContextImpl;
import ch.bfh.ti.sed.patmon1.server.controller.Session;
import ch.bfh.ti.sed.patmon1.server.controller.impl.SessionStorageImpl;
import ch.bfh.ti.sed.patmon1.server.model.sbo.DoctorSBO;

/**
 * Test class for {@link SessionStorageImpl}.
 */
public class SessionStorageTest {

	ApplicationContext context = new ApplicationContextImpl();

	/**
	 * Tests the creation of a session.
	 */
	@Test
	public void testCreateAndRegisterSession() {
		SessionStorageImpl storage = new SessionStorageImpl(context);
		assertEquals(0, storage.getActiveSessions().size());

		DoctorSBO doctor = new DoctorSBO("email", "secure");

		Session session = storage.createAndRegisterSession(doctor);

		Map<Session, SessionController> activeSessions = storage
				.getActiveSessions();
		assertEquals(1, activeSessions.size());
		assertNotNull(activeSessions.get(session));
		assertEquals(doctor, activeSessions.get(session).getDoctor());
	}

	/**
	 * Test the dropping of a session.
	 */
	@Test
	public void testDropSession() {
		SessionStorageImpl storage = new SessionStorageImpl(context);
		DoctorSBO doctor = new DoctorSBO("email", "secure");
		Session session = storage.createAndRegisterSession(doctor);

		Map<Session, SessionController> activeSessions = storage
				.getActiveSessions();
		assertEquals(1, activeSessions.size());
		assertNotNull(storage.validateAndGetSessionController(session));

		storage.dropSession(session);

		assertEquals(0, activeSessions.size());
		assertNull(storage.validateAndGetSessionController(session));
	}

	/**
	 * Tests if validate and get session controller returns the correct session
	 * controller.
	 */
	@Test
	public void testValidateSession() {
		SessionStorageImpl storage = new SessionStorageImpl(context);
		DoctorSBO doctor = new DoctorSBO("email", "secure");
		Session session = storage.createAndRegisterSession(doctor);

		Map<Session, SessionController> activeSessions = storage
				.getActiveSessions();
		assertEquals(1, activeSessions.size());

		SessionController sessionController = storage
				.validateAndGetSessionController(session);
		assertEquals(activeSessions.get(session), sessionController);
	}
}
