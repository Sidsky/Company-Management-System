
public class CmdStartNewDay extends RecordedCommand{

	Day old;
	Day n;
	@Override
	public void execute(String[] cmdParts) {
		old=SystemDate.getInstance().clone();
		SystemDate.getInstance().set(cmdParts[1]);
		n=SystemDate.getInstance().clone();
		addUndoCommand(this);
		clearRedoList();
		System.out.println("Done.");
	}

	@Override
	public void undoMe() {
		SystemDate.getInstance().set(old.toString());
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe() {
		SystemDate.getInstance().set(n.toString());
		addUndoCommand(this);
		
	}

}
