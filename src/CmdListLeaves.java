
public class CmdListLeaves implements Command
{

	@Override
	public void execute(String[] cmdParts)
	{
		Company co=Company.getInstance();
		if(cmdParts.length==1)
			co.listLeaves();
		else
		{
			try {
				if(cmdParts.length>2)
					throw new ExInsufficientArguments();
				co.findEmployee(cmdParts[1]).listLeaves();
			} catch (ExEmployeeNotFound e) {
				System.out.println(e.getMessage());
			} catch (ExInsufficientArguments e) {
				System.out.println(e.getMessage());
			}
		}
			
	}
	

}



