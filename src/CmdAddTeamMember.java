
public class CmdAddTeamMember extends RecordedCommand {

	private Team t;
	private Employee e;
	@Override
	public void execute(String[] cmdParts) 
	{
		try {
			if(cmdParts.length!=3)
				throw new ExInsufficientArguments();
			Company co=Company.getInstance();
			t=co.findTeam(cmdParts[1]);
			e=co.findEmployee(cmdParts[2]);
			t.EnlistMember(e);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExMemberAlreadyJoinedTeam e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void undoMe()
	{
		t.removeTeamMember(e);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		t.addTeamMember(e,new RNormalEmployee(t));
		addUndoCommand(this);
		
	}

}
