import java.util.ArrayList;

public class LeaveRecordHead extends LeaveRecord {
	
	private ArrayList<RActingHead> aHead;  

	public LeaveRecordHead(String aStart, String aEnd) {
		super(aStart, aEnd);
		aHead=new ArrayList<>();
		
	}
	
	public void addActingHeads(RActingHead AH)
	{
		aHead.add(AH);
	}
	
	@Override
	public void removal() 
	{
		
		for(RActingHead AH:aHead)
		{
			AH.getTeam().removeActingHead(AH);
		}
	}
	
	@Override
	public void addRoles()
	{
		for(RActingHead AH:aHead)
		{
			AH.getTeam().addActingHead(AH);
		}
	}
}


