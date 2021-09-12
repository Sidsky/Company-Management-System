import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team implements Comparable<Team>{
	
	private String teamName;
	private Employee head;
	private Day dateSetup;
	private List<Employee> TeamMembers;
	private List<RActingHead> ActingHead;
	
	public Team(String n, Employee hd)
	{
		TeamMembers=new ArrayList<>();
		this.teamName=n;
		this.head=hd;
		head.addRole(new RHead(this));
		TeamMembers.add(head);
		this.dateSetup=SystemDate.getInstance().clone();
		ActingHead=new ArrayList<>();
		
	}
	
	public String getName()
	{
		return this.teamName;
	}
	
	public static void list(ArrayList<Team> list)
	{
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date" );
		
		for(Team t: list)
		{
			System.out.printf(
					"%-30s%-10s%-13s\n",
					t.teamName,
					t.head.getName(),
					t.dateSetup);
					
		}
	}
	
	public static Team searchTeam(ArrayList<Team> list,String sWanted)
	{
		for(Team t:list)
		{
			if(t.getName().equals(sWanted))
			{
				return t;
			}
		}
		return null;
	}
	
	public Employee searchTeamMember(String name) throws ExEmployeeNotFoundOnTeam
	{
		for(Employee e:TeamMembers)
		{
			if(e.getName().equals(name))
				return e;
		}
		throw new ExEmployeeNotFoundOnTeam(String.format("Employee (%s) not found for %s!", name,this.getName()));
	}
	
	public void EnlistMember(Employee e) throws ExMemberAlreadyJoinedTeam
	{
		if(!this.TeamMembers.isEmpty() && this.TeamMembers.contains(e) )
			throw new ExMemberAlreadyJoinedTeam();
		this.TeamMembers.add(e);
		e.addRole(new RNormalEmployee(this));
		Collections.sort(this.TeamMembers);
			
	}
	
	public void addTeamMember(Employee e,Role r)
	{
		this.TeamMembers.add(e);
		e.addRole(r);
		Collections.sort(this.TeamMembers);
	}
	
	public void addActingHead(RActingHead AH)
	{
		this.ActingHead.add(AH);
		Collections.sort(ActingHead);
	}
	public void removeActingHead(RActingHead AH)
	{
		this.ActingHead.remove(AH);
		Collections.sort(ActingHead);
	}
	
	public void removeTeamMember(Employee e)
	{
		this.TeamMembers.remove(e);
		e.removeRole(this);
	}
	
	public void listTeamMembers() 
	{
		for(Employee e:TeamMembers)
		{
			System.out.println(e.getName()+e.findRole(this).genMessage());
		}
		if(!ActingHead.isEmpty())
			this.listActingHeads();
	}
	
	public void listActingHeads() {
		
		System.out.println("Acting heads:");
		for(RActingHead AH: ActingHead)
		{
			System.out.println(AH.genMessage());
		}
		
	}

	@Override
	public int compareTo(Team another)
	{
		return this.teamName.compareTo(another.teamName);
	}

	public void removeLeader()
	{
		removeTeamMember(this.head);
		
	}

	public static ArrayList<Role> getActingHeadRoles(ArrayList<Team> allTeams, Employee e) 
	{
		ArrayList<Role> aHead=new ArrayList<>();
		for(Team t:allTeams)
		{
			if(!t.ActingHead.isEmpty())
			{
				for(RActingHead AH:t.ActingHead)
				{
					if(AH.getEmployee()==e)
						aHead.add(AH);
				}
			}
			
		}
		return aHead;
	}


}
