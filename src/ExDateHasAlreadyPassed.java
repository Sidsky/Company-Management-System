
public class ExDateHasAlreadyPassed extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExDateHasAlreadyPassed()
	{
		super("Wrong Date!");
	}
	
	public ExDateHasAlreadyPassed(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
