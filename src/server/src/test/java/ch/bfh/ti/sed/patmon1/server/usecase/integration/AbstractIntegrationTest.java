package ch.bfh.ti.sed.patmon1.server.usecase.integration;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;

/**
 * Superclass for all integration tests (test which use the webservice). Mutes
 * the JAX-WS log by setting the logging-level to WARNING.
 */
public abstract class AbstractIntegrationTest {

	@BeforeClass
	public static void setClassUp() {
		// We don't want INFO logging from JAX-WS
		Logger.getLogger("javax.enterprise.resource.webservices.jaxws.server")
				.setLevel(Level.WARNING);
	}
}
