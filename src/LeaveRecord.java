

public class LeaveRecord implements Comparable<LeaveRecord>{
	
	private Day start;
	private Day end;
	private int duration;
	
	public LeaveRecord(String aStart,String aEnd)
	{
		start=new Day(aStart);
		end=new Day(aEnd);
		duration=Day.duration(new Day(start.toString()), end);
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	

	
	public boolean overlap(LeaveRecord lr2) throws ExLeaveOverlapped
	{
		if((this.start.compareTo(lr2.start)<0 && this.end.compareTo(lr2.start)<0)||(this.start.compareTo(lr2.end)>0 && this.end.compareTo(lr2.end)>0))
		{
			return true;
		}
		else
		{
			throw new ExLeaveOverlapped(String.format("Leave overlapped: the leave period %s to %s is found!", lr2.start.toString(),lr2.end.toString()));
		}
	}
	
	public boolean overlap(RActingHead AH,String name) throws ExLeaveOverlapped
	{
		if((this.start.compareTo(AH.getStart())<0 && this.end.compareTo(AH.getStart())<0)||(this.start.compareTo(AH.getEnd())>0 && this.end.compareTo(AH.getEnd())>0))
		{
			return true;
		}
		else
		{
			throw new ExLeaveOverlapped(String.format("Cannot take leave.  %s is the acting head of %s during %s to %s!",name, AH.getTeamName(), AH.getStart().toString(),AH.getEnd().toString()));
		}
	}
	
	
	
	public Day getStart()
	{
		return this.start;
	}
	
	public Day getEnd()
	{
		return this.end;
	}
	
	@Override
	public String toString() 
	{
		return String.format("%s to %s", this.start,this.end);
	}

	@Override
	public int compareTo(LeaveRecord lr) {
		return this.start.compareTo(lr.start);
	}
	
	public void removal() {}

	public void addRoles() {}

}
