package ch.bfh.ti.sed.patmon1.client.cli.commands;

import ch.bfh.ti.sed.patmon1.client.cli.TerminalCommand;
import ch.bfh.ti.sed.patmon1.client.cli.TerminalWindow;
import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;

public class LogOutCommand implements TerminalCommand {

	@Override
	public String getCommandName() {
		return "logout";
	}

	@Override
	public String getCommandDescription() {
		return "do a logout";
	}

	@Override
	public void execute(Session session,
			SessionControllerPort sessionController, TerminalWindow terminal,
			String input) throws InvalidSessionException {
			terminal.addLine("Logged out..");
			terminal.addLine("Username:");
			sessionController.logout(session);
	}

}
