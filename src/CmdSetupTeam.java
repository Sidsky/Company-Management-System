
public class CmdSetupTeam extends RecordedCommand {

	private Employee head;
	private Team t;
	@Override
	public void execute(String[] cmdParts) 
	{
		try {
			if(cmdParts.length <3)
				throw new ExInsufficientArguments();
			Company co=Company.getInstance();
			head=co.findEmployee(cmdParts[2]);
			t=co.createTeam(cmdParts[1], head);
			
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExTeamAlreadyExists e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		Company co=Company.getInstance();
		t.removeTeamMember(head);
		co.removeTeam(t);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		Company co=Company.getInstance();
		co.addTeam(t);
		t.addTeamMember(head, new RHead(t));
		addUndoCommand(this);
		
	}

}
