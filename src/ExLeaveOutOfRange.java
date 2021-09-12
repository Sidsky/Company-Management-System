
public class ExLeaveOutOfRange extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExLeaveOutOfRange()
	{
		super("Out of range (Entitled Annual Leaves should be within 0-300)!");
	}
	
	public ExLeaveOutOfRange(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
