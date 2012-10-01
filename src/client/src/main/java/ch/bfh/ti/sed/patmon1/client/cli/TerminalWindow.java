package ch.bfh.ti.sed.patmon1.client.cli;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

/**
 * draws the frame for the terminal
 * @author hip4
 *
 */
public class TerminalWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommandView commandView;
	private List<TerminalInputListener> terminalInputListeners = new ArrayList<TerminalInputListener>();
	
	public TerminalWindow() {
		super("PatientMonitor 1.0");
		commandView = new CommandView();
		setSize(500,500);
		getContentPane().add(commandView);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * writes the given line to the terminal
	 * and adds a carriage return. Moves the
	 * cursor to the last line
	 * @param line
	 */
	public void addLine(String line){
		commandView.addLine(line);
	}
	
	private class CommandView extends JEditorPane{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String line ="";
		public CommandView() {
		
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent keyEvent) {
					if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
						notifyListeners(line);
						line = "";
					} else {
						line += keyEvent.getKeyChar();
					}
				}
			});
		}
		/**
		 * writes the given line to the terminal
		 * and adds a carriage return. Moves the
		 * cursor to the last line
		 * @param line
		 */
		public void addLine(String line){
			setText(getText()+line+"\n");
			this.setCaretPosition(getText().length());
		}
	}
	
	/**
	 * Notifies all listeners and sends them the new input
	 * @param line
	 */
	private void notifyListeners(String line){
		for(TerminalInputListener l: terminalInputListeners)
			l.notify(line);
	}

	/**
	 * adds the given listener
	 * @param listener
	 */
	public void addTerminalInputListener(TerminalInputListener listener){
		terminalInputListeners.add(listener);
	}
}
