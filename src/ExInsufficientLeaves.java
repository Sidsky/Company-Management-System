
public class ExInsufficientLeaves extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExInsufficientLeaves()
	{
		super("Insufficient Balance!");
	}
	
	public ExInsufficientLeaves(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
