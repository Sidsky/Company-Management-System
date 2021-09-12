import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee implements Comparable<Employee>{
	
	private String name;
	private int yrLeaveEntitled;
	private List<LeaveRecord> yleaves;
	private List<Role> roles;
	
	public Employee(String n,int yle)
	{
		this.name=n;
		this.yrLeaveEntitled=yle;
		yleaves=new ArrayList<>();
		roles=new ArrayList<>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public static Employee searchEmployee(ArrayList<Employee> list,String sWanted)
	{
		for(Employee e:list)
		{
			if(e.getName().equals(sWanted))
			{
				return e;
			}
		}
		return null;
	}
	
	@Override
	public int compareTo(Employee another)
	{
		return this.name.compareTo(another.name);
	}

	public static void list(ArrayList<Employee> allEmployees) 
	{
		
		for(Employee e: allEmployees)
		{
			System.out.printf("%s (Entitled Annual Leaves: %d days)\n",e.name,e.yrLeaveEntitled);
		}
		System.out.println();
		
	}
	
	public LeaveRecord createLeave(String cmdParts[]) throws ExLeaveOverlapped, ExDateHasAlreadyPassed, ExInsufficientLeaves, ExInsufficientArguments, ExTeamNotFound, ExEmployeeNotFoundOnTeam
	{
		LeaveRecord lr =new LeaveRecord(cmdParts[2],cmdParts[3]);
		if(lr.getStart().compareTo(SystemDate.getInstance())<0)
			throw new ExDateHasAlreadyPassed(String.format("Wrong Date. System date is already %s!", SystemDate.getInstance().toString()));
		if(lr.getDuration()>this.yrLeaveEntitled)
			throw new ExInsufficientLeaves(String.format("Insufficient balance. %d days left only!", this.yrLeaveEntitled));
			
		for(LeaveRecord leaves:yleaves)
		{
			if(lr.overlap(leaves))
				continue;
		}
		
		ArrayList<Role> head=this.getHeadRoles();
		if(!head.isEmpty())
		{
			if(cmdParts.length<4)
			{
				throw new ExInsufficientArguments(String.format("Missing input:  Please give the name of the acting head for %s", head.get(0).getTeamName()));
			}
			else
			{
				lr=this.AllotActingHeads(head,cmdParts);
				
			}
			
		}
		ArrayList<Role> RAhead=this.getActingHeadRoles();
		if(!RAhead.isEmpty())
		{
			for(Role r:RAhead)
			{
				if(lr.overlap((RActingHead)r,this.name))
					continue;
			}
		}
		
		yleaves.add(lr);
		this.changeLeaves(lr.getDuration());
		Collections.sort(this.yleaves);
		return lr;
	}
	
	public LeaveRecordHead AllotActingHeads(ArrayList<Role> head, String[] cmdParts) throws ExInsufficientArguments, ExTeamNotFound,ExEmployeeNotFoundOnTeam, ExLeaveOverlapped 
	{
		LeaveRecordHead lr=new LeaveRecordHead(cmdParts[2],cmdParts[3]);
		Company co=Company.getInstance();
		for(Role r:head)
		{
			boolean found=false;
			for(int i=4;i<cmdParts.length;i+=2)
			{
				if(co.findTeam(cmdParts[i]).getName().equals(r.getTeamName()))
				{
					found=true;
					Employee e=co.findTeam(cmdParts[i]).searchTeamMember(cmdParts[i+1]);
					RActingHead ah=new RActingHead(co.findTeam(cmdParts[i]),e,cmdParts[2],cmdParts[3]);
					
					for(LeaveRecord leaves:e.yleaves)
					{
						if(ah.overlap(leaves))
							continue;
					}
					
				}
			}
			if(!found)
			{
				
				throw new ExInsufficientArguments(String.format("Missing input:  Please give the name of the acting head for %s", r.getTeamName()));
			}
		}
			for(int i=4;i<cmdParts.length;i+=2)
			{
				Employee e=co.findTeam(cmdParts[i]).searchTeamMember(cmdParts[i+1]);
				RActingHead ah=new RActingHead(co.findTeam(cmdParts[i]),e,cmdParts[2],cmdParts[3]);
				co.findTeam(cmdParts[i]).addActingHead(ah);
				lr.addActingHeads(ah);
			}
			return lr;
	}
		
	

	public void addLeaves(LeaveRecord lr)
	{
		lr.addRoles();
		yleaves.add(lr);
		this.changeLeaves(lr.getDuration());
	}
	
	public void removeLeaves(LeaveRecord lr)
	{
		lr.removal();
		yleaves.remove(lr);
		this.changeLeaves(-(lr.getDuration()));
	}
	
	public void changeLeaves(int days)
	{
		this.yrLeaveEntitled-=days;
	}
	public void listLeaves()
	{
		
		if(this.yleaves.size()>0)
		{
			for(LeaveRecord lr:this.yleaves)
			{
				System.out.println(lr.toString());
			}
		}
		else
		{
			System.out.println("No leave record");
		}
		
	}
	
	public void addRole(Role r)
	{
		this.roles.add(r);
		Collections.sort(this.roles);
	}
	
	public void removeRole(Team t)
	{
		
		roles.remove(findRole(t));
		
		Collections.sort(this.roles);
	}
	public void removeRole(Role r)
	{
		
		roles.remove(r);
		
		Collections.sort(this.roles);
	}
	
	public Role findRole(Team t)
	{
	
		for(Role rl:roles)
		{
			if(rl.getTeamName().equals(t.getName()))
				return rl;
		}
		return null;
	}
	
	public void listRoles()
	{
		if(!roles.isEmpty())
		{
			for(Role r:roles)
			{
				System.out.println(r.getTeamName()+r.genMessage());
			}
		}
		else
		{
			System.out.println("No role");
		}
	}
	
	public ArrayList<Role> getHeadRoles()
	{
		ArrayList<Role> head=new ArrayList<>();
		for(Role r:roles)
		{
			if(r instanceof RHead)
				head.add(r);
		}
		return head;
	}
	

	
	public ArrayList<Role> getActingHeadRoles()
	{
		return Company.getInstance().getActingHeadRoles(this);
	}

}
