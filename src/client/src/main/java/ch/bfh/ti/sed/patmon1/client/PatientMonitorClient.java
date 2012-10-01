package ch.bfh.ti.sed.patmon1.client;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.ti.sed.patmon1.client.cli.TerminalCommand;
import ch.bfh.ti.sed.patmon1.client.cli.TerminalInputListener;
import ch.bfh.ti.sed.patmon1.client.cli.TerminalWindow;
import ch.bfh.ti.sed.patmon1.client.cli.commands.ListPatientsCommand;
import ch.bfh.ti.sed.patmon1.client.cli.commands.LogOutCommand;
import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerPort;
import ch.bfh.ti.sed.patmon1.ws.LoginControllerService;
import ch.bfh.ti.sed.patmon1.ws.NotLoggedInException;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerService;

public final class PatientMonitorClient implements TerminalInputListener {

	private LoginControllerService service = new LoginControllerService();
	private LoginControllerPort loginController = service
			.getLoginControllerPort();
	private LoginState loginState;
	private String username, password;
	private Session session;
	private List<TerminalCommand> terminalCommands = new ArrayList<TerminalCommand>();

	private final TerminalWindow terminal;

	PatientMonitorClient(TerminalWindow terminal) {
		this.terminal = terminal;
		terminal.addTerminalInputListener(this);
		initalize();
		terminalCommands.add(new LogOutCommand());
		terminalCommands.add(new ListPatientsCommand());
	}

	private final void initalize() {
		terminal.addLine("Patient Monitor Client 1.0");
		terminal.addLine("Doctor Login:");
		loginState = LoginState.WAITING_FOR_USERNAME;
	}

	@Override
	public void notify(String line) {
		line = line.trim();
		switch (loginState) {
		case WAITING_FOR_USERNAME:
			username = line;
			loginState = LoginState.WAITING_FOR_PASSWORD;
			terminal.addLine("password:");
			break;
		case WAITING_FOR_PASSWORD:
			password = line;
			try {
				session = loginController.login(username, password);
				loginState = LoginState.LOGGED_IN;
				terminal.addLine("successful");
				listCommands();

			} catch (NotLoggedInException e) {
				terminal.addLine("Login unsuccessful. Try again.");
				terminal.addLine("Username:");
				loginState = LoginState.WAITING_FOR_USERNAME;
			}
			break;
		case LOGGED_IN:
			SessionControllerService service = new SessionControllerService();
			SessionControllerPort sessionController = service
					.getSessionControllerPort();

			for (TerminalCommand command : terminalCommands) {
				try {
					if (command.getCommandName().equals(line))
						command.execute(session, sessionController, terminal,
								line);
				} catch (InvalidSessionException e) {
					terminal.addLine("session timeout");
				}
			}
			break;
		}
	}

	
	/**
	 * list all commands
	 */
	private void listCommands(){
		for(TerminalCommand command: terminalCommands){
			terminal.addLine(command.getCommandName() + "\t" + command.getCommandDescription());
		}
	}
	
	
	/**
	 * Starts the patmon1 client.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TerminalWindow window = new TerminalWindow();
		new PatientMonitorClient(window);
	}

}
