
public class CmdListTeamMembers implements Command{
	
	@Override
	public void execute (String[] cmdParts)
	{
		try {
			if(cmdParts.length<1)
				throw new ExInsufficientLeaves();
			Company co=Company.getInstance();
			if(cmdParts.length==1)
			{
				co.listTeamsAndMembers();
			}
			else
			{
				co.findTeam(cmdParts[1]).listTeamMembers();
			}
			
		} catch (ExInsufficientLeaves e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}
		
	}

}
