package ch.bfh.ti.sed.patmon1.server.context.impl;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.domain.BusinessHandlerFactory;
import ch.bfh.ti.sed.patmon1.server.domain.impl.BusinessHandlerFactoryImpl;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.gtk.mock.GatekeeperFactoryImpl;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;
import ch.bfh.ti.sed.patmon1.server.persistence.mock.MockEntityManager;

/**
 * Implementation for {@link ApplicationContext}.
 */
public class ApplicationContextImpl implements ApplicationContext {

	private EntityManager entityManager;
	private BusinessHandlerFactory domainHandlerFactory;
	private GatekeeperFactory gatekeeperFactory;

	public ApplicationContextImpl() {
		entityManager = new MockEntityManager();
		domainHandlerFactory = new BusinessHandlerFactoryImpl(this);
		gatekeeperFactory = new GatekeeperFactoryImpl();
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public BusinessHandlerFactory getBusinessHandlerFactory() {
		return domainHandlerFactory;
	}

	@Override
	public GatekeeperFactory getGatekeeperFactory() {
		return gatekeeperFactory;
	}

}
