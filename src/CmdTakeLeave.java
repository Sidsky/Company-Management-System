
public class CmdTakeLeave extends RecordedCommand {
	
	private Employee e;
	private LeaveRecord lr;

	@Override
	public void execute(String[] cmdParts)
	{
	
		try {

			if(cmdParts.length <4)
				throw new ExInsufficientArguments();
			
			Company co=Company.getInstance();
			e=co.findEmployee(cmdParts[1]);
			
			lr=e.createLeave(cmdParts);
			
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExLeaveOverlapped e) {
			System.out.println(e.getMessage());
		} catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientLeaves e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFoundOnTeam e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() 
	{
		e.removeLeaves(lr);
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe()
	{
		
		e.addLeaves(lr); 
		addUndoCommand(this);
		
		
	}

}
