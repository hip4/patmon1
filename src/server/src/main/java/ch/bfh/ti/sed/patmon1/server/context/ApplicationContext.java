package ch.bfh.ti.sed.patmon1.server.context;

import ch.bfh.ti.sed.patmon1.server.domain.BusinessHandlerFactory;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

/**
 * The application context contains reference to application-wide shared
 * components.
 */
public interface ApplicationContext {

	/**
	 * @return The application's EntityManager.
	 */
	public EntityManager getEntityManager();

	/**
	 * @return The application's BusinessHandlerFactory.
	 */
	public BusinessHandlerFactory getBusinessHandlerFactory();

	/**
	 * @return The application's GatekeeperFactory.
	 */
	public GatekeeperFactory getGatekeeperFactory();

}