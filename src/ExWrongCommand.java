
public class ExWrongCommand extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExWrongCommand()
	{
		super("Wrong Command");
	}
	
	public ExWrongCommand(String msg) 
	{
		super (msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
	}

}
