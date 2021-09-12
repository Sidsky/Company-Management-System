
public class CmdListRoles implements Command{
	
	@Override
	public void execute (String[] cmdParts)
	{
		try {
			if(cmdParts.length!=2)
				throw new ExInsufficientLeaves();
			
			Company co=Company.getInstance();
			co.findEmployee(cmdParts[1]).listRoles();
		} catch (ExInsufficientLeaves e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}
		
	}

}
