package ch.bfh.ti.sed.patmon1.server;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.context.impl.ApplicationContextImpl;
import ch.bfh.ti.sed.patmon1.server.gtk.GatekeeperFactory;
import ch.bfh.ti.sed.patmon1.server.persistence.EntityManager;

/**
 * Class which sets up a {@link PatientMonitorServer} which can be used for
 * testing purposes.
 * 
 * @author burgc5
 */
public class PatientMonitorServerTest {

	/**
	 * Static factory method for end-to-end tests which need a
	 * {@link PatientMonitorServer} to run.
	 * 
	 * @param em
	 *            The {@link EntityManager} to base on
	 * @return a new instance of {@link PatientMonitorServer} for testing
	 *         purposes
	 */
	public static PatientMonitorServer usingCustom(EntityManager em) {
		ApplicationContext applicationContext = new TestApplicationContext(em, null);
		PatientMonitorServer server = new PatientMonitorServer(
				applicationContext);
		return server;
	}
	
	/**
	 * Static factory method for end-to-end tests which need a
	 * {@link PatientMonitorServer} to run.
	 * 
	 * @param em
	 *            The {@link EntityManager} to base on
	 * @param gf
	 *            The {@link GatekeeperFactory} to base on
	 * @return a new instance of {@link PatientMonitorServer} for testing
	 *         purposes
	 */
	public static PatientMonitorServer usingCustom(EntityManager em, GatekeeperFactory gf) {
		ApplicationContext applicationContext = new TestApplicationContext(em, gf);
		PatientMonitorServer server = new PatientMonitorServer(
				applicationContext);
		return server;
	}

	/**
	 * An ApplicationContext which uses a specific EntityManager.
	 * 
	 * @author burgc5
	 */
	private static class TestApplicationContext extends ApplicationContextImpl {

		private EntityManager em;
		private GatekeeperFactory gf;

		public TestApplicationContext(EntityManager em, GatekeeperFactory gf) {
			this.em = em;
			this.gf = gf;
		}

		@Override
		public EntityManager getEntityManager() {
			if (em != null) {
				return em;
			}
			return super.getEntityManager();
		}
		
		@Override
		public GatekeeperFactory getGatekeeperFactory() {
			if (gf != null) {
				return gf;
			}
			return super.getGatekeeperFactory();
		}
	}

}
