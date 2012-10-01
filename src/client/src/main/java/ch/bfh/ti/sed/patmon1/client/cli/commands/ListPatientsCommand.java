package ch.bfh.ti.sed.patmon1.client.cli.commands;

import ch.bfh.ti.sed.patmon1.client.cli.TerminalCommand;
import ch.bfh.ti.sed.patmon1.client.cli.TerminalWindow;
import ch.bfh.ti.sed.patmon1.ws.InvalidSessionException;
import ch.bfh.ti.sed.patmon1.ws.Patient;
import ch.bfh.ti.sed.patmon1.ws.PatientList;
import ch.bfh.ti.sed.patmon1.ws.Session;
import ch.bfh.ti.sed.patmon1.ws.SessionControllerPort;

public class ListPatientsCommand implements TerminalCommand{

	@Override
	public String getCommandName() {
		return "list patients";
	}

	@Override
	public String getCommandDescription() {
		return "Lists all patients";
	}

	@Override
	public void execute(Session session, SessionControllerPort sessionController,
			TerminalWindow terminal, String input) throws InvalidSessionException {
	
		PatientList patients = sessionController.getPatients(session);
		for (Patient patient : patients.getPatients()) {
			terminal.addLine(patient.getSSN() + " - " + patient.getName());
		}
		if (patients.getPatients().size() == 0) {
			terminal.addLine("No Patients found..");
		}	
		
		
	}

}
