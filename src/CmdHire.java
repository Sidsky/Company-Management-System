
public class CmdHire extends RecordedCommand {
	
	private Employee e;

	@Override
	public void execute(String[] cmdParts) 
	{
	
		try {
			if(cmdParts.length <3)
				throw new ExInsufficientArguments();
			
			Company co=Company.getInstance();
			e=co.createEmployee(cmdParts[1], Integer.parseInt(cmdParts[2]));
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeAlreadyExists e) {
			System.out.println(e.getMessage());
		} catch (ExLeaveOutOfRange e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() 
	{
		Company co=Company.getInstance();
		co.removeEmployee(e);
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe()
	{
		Company co=Company.getInstance();
		co.addEmployee(e);
		addUndoCommand(this);
		
		
	}

}
