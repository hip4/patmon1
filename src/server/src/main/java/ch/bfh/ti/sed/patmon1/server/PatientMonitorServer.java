package ch.bfh.ti.sed.patmon1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;
import ch.bfh.ti.sed.patmon1.server.context.impl.ApplicationContextImpl;
import ch.bfh.ti.sed.patmon1.server.controller.LoginController;
import ch.bfh.ti.sed.patmon1.server.controller.SessionControllerProxy;
import ch.bfh.ti.sed.patmon1.server.controller.impl.LoginControllerImpl;
import ch.bfh.ti.sed.patmon1.server.controller.impl.SessionControllerProxyImpl;

/**
 * Main class for the server.
 * 
 * @author buergich
 */
public class PatientMonitorServer {

	private static final String LOGIN_URL_KEY = "login.endpoint.url";
	private static final String SESSION_URL_KEY = "session.endpoint.url";

	private ApplicationContext context;

	private String loginEndpointUrl;
	private String sessionEndpointUrl;

	private final ServicePublisher publisher = new ServicePublisher();

	public PatientMonitorServer() {
		this(new ApplicationContextImpl());
	}
	
	PatientMonitorServer(ApplicationContext context) {
		this.context = context;
		
		ResourceBundle bundle = ResourceBundle.getBundle("service");
		loginEndpointUrl = bundle.getString(LOGIN_URL_KEY);
		sessionEndpointUrl = bundle.getString(SESSION_URL_KEY);
		
		// We don't want JAX-WS INFO logging
		Logger.getLogger("javax.enterprise.resource.webservices.jaxws.server")
				.setLevel(Level.WARNING);
	}

	public synchronized void start() {
		publisher.shouldRun = true;
		new Thread(publisher).start();
		
		// wait for server to come up
		while (!publisher.started) {
			try {
				// do not call Thread.sleep() here because we are holding the
				// class' lock
				this.wait(10);
			} catch (InterruptedException e) {
				// No problem
			}
		}
	}

	public synchronized void stop() {
		publisher.shouldRun = false;
		
		// wait for server to shut down
		while (publisher.started) {
			try {
				// do not call Thread.sleep() here because we are holding the
				// class' lock
				this.wait(10);
			} catch (InterruptedException e) {
				// No problem
			}
		}
	}

	/**
	 * Starts the patmon1 server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Patient Monitor Server");
		PatientMonitorServer server = new PatientMonitorServer();
		
		System.out.println("\nStarting up...");
		server.start();
		System.out.println("Server started");
		
		// Wait for user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			do {
				System.out.print("Enter 'exit' to shut down: ");
			} while (!"exit".equals(br.readLine()));
		} catch (IOException ioe) {
			System.exit(1);
		}
		
		server.stop();
		System.out.println("Shutdown complete");
	}

	/**
	 * @author buergc5
	 *
	 */
	private class ServicePublisher implements Runnable {

		private boolean shouldRun = true;
		private boolean started = false;

		@Override
		public void run() {
			SessionControllerProxy sessionController = new SessionControllerProxyImpl(
					PatientMonitorServer.this.context);
			Endpoint sessionEndpoint = Endpoint.create(sessionController);
			
			LoginController loginController = new LoginControllerImpl(
					PatientMonitorServer.this.context, sessionController);
			Endpoint loginEndpoint = Endpoint.create(loginController);

			loginEndpoint.publish(PatientMonitorServer.this.loginEndpointUrl);
			sessionEndpoint.publish(PatientMonitorServer.this.sessionEndpointUrl);
			started = true;
			while (shouldRun) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// Should be no problem
				}
			}
			loginEndpoint.stop();
			sessionEndpoint.stop();
			started = false;
		}

	}

}
