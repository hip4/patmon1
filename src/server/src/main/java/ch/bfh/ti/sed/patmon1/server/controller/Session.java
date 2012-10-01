package ch.bfh.ti.sed.patmon1.server.controller;

import java.util.UUID;

/**
 * A Session is used to store an authenticated doctor session it the
 * {@link SessionStorage}. It is handed over to the client and the client has to
 * authenticate each request using it's session.
 * <p>
 * There is no interface for this class since JAX-WS does not support interfaces
 * to be sent over to the client.
 */
public class Session {

	private String sessionId;

	/**
	 * Initalizes a session by creating a random session id using {@link UUID}.
	 */
	public void initialize() {
		sessionId = UUID.randomUUID().toString();
	}

	/**
	 * @return the session's id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 * 
	 * @param sessionId
	 *            the id to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Session))
			return false;
		Session other = (Session) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

}
