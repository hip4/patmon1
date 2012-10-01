package ch.bfh.ti.sed.patmon1.server.context;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.ti.sed.patmon1.server.context.impl.ApplicationContextImpl;
import ch.bfh.ti.sed.patmon1.server.domain.BusinessHandlerFactory;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

/**
 * Test class for {@link ApplicationContextImpl}
 */
public class ApplicationContextTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() {
		applicationContext = new ApplicationContextImpl();
	}

	/**
	 * Tests if the entity manager on the application context is not null.
	 */
	@Test
	public void testGetEntityManagerNotEmpty() {
		EntityManager entityManager = applicationContext.getEntityManager();
		assertNotNull(entityManager);
	}

	/**
	 * Tests if the business handler factory on the application context is not
	 * null.
	 */
	@Test
	public void testGetBusinessHandlerFactory() {
		BusinessHandlerFactory factory = applicationContext
				.getBusinessHandlerFactory();
		assertNotNull(factory);
	}
	
	/**
	 * Test if the gatekeeper factory on the application context is not null.
	 */
	@Test
	public void testGetGatekeeperFactory() {
		GatekeeperFactory factory = applicationContext.getGatekeeperFactory();
		assertNotNull(factory);
	}

}
