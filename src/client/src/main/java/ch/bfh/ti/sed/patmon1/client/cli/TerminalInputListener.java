package ch.bfh.ti.sed.patmon1.client.cli;

public interface TerminalInputListener {
	/**
	 * receives the new input
	 * @param line
	 */
	public void notify(String line);
}
