package ch.bfh.ti.sed.patmon1.client.cli;

import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;

public interface TerminalCommand {
	/**
	 * returns the command name (% for white spaces)
	 * @return command name
	 */
	public String getCommandName();
	
	/**
	 * @return the description of the command
	 */
	public String getCommandDescription();
	
	/**
	 * executes the command
	 * @param session
	 * @param terminal
	 * @param input
	 */
	public void execute(Session session,SessionControllerPort sessionController,TerminalWindow terminal, String input) throws InvalidSessionException;
}
