
public class ExEmployeeNotFoundOnTeam extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExEmployeeNotFoundOnTeam()
	{
		super("Employee not found on team!");
	}
	
	public ExEmployeeNotFoundOnTeam(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
