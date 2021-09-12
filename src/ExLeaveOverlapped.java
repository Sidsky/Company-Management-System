
public class ExLeaveOverlapped extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExLeaveOverlapped()
	{
		super("Leave overlapped!");
	}
	
	public ExLeaveOverlapped(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
