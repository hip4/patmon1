package ch.bfh.ti.sed.patmon1.server.controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for {@link Session}.
 */
public class SessionTest {

	/**
	 * Tests the initialization of a session.
	 */
	@Test
	public void testInitialize() {
		Session s = new Session();

		assertNull(s.getSessionId());

		s.initialize();

		assertNotNull(s.getSessionId());
	}

	/**
	 * Tests the setter and getter of the session id.
	 */
	@Test
	public void testSessionId() {
		Session s = new Session();

		s.setSessionId("Session-ID");

		assertEquals("Session-ID", s.getSessionId());
	}

	/**
	 * Tests equality.
	 */
	@Test
	public void testEquals() {
		Session s = new Session();
		s.initialize();
		Session s2 = new Session();
		s.setSessionId("other id");

		assertEquals(s, s);
		assertEquals(s.hashCode(), s.hashCode());
		assertFalse(s.equals(s2));
		assertFalse(s2.equals(s));
	}
}
