
public class RActingHead extends Role 
{
	private Employee e;
	private Day start;
	private Day end;
	public RActingHead(Team aT,Employee aE,String aStart,String aEnd)
	{
		super(aT);
		e=aE;
		start=new Day(aStart);
		end=new Day(aEnd);
	}
	
	public Day getStart()
	{
		return this.start;
	}
	
	public Employee getEmployee()
	{
		return this.e;
	}
	
	public Day getEnd()
	{
		return this.end;
	}

	@Override
	public String genMessage()
	{
		
		return String.format("%s to %s: %s", this.start,this.end,e.getName());
	}
	
	public boolean overlap(LeaveRecord lr2) throws ExLeaveOverlapped
	{
		if((this.start.compareTo(lr2.getStart())<0 && this.end.compareTo(lr2.getStart())<0)||(this.start.compareTo(lr2.getEnd())>0 && this.end.compareTo(lr2.getEnd())>0))
		{
			return true;
		}
		else
		{
			throw new ExLeaveOverlapped(String.format("%s is on leave during %s to %s!",e.getName(), lr2.getStart().toString(),lr2.getEnd().toString()));
		}
	}
	
	@Override
	public int compareTo(Role Another)
	{
		
		return this.start.compareTo(((RActingHead)Another).getStart());  
	}
	
	
}
