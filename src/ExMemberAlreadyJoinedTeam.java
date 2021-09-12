
public class ExMemberAlreadyJoinedTeam extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExMemberAlreadyJoinedTeam()
	{
		super("The employee has joined the team already!");
	}
	
	public ExMemberAlreadyJoinedTeam(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
				
	}

}
