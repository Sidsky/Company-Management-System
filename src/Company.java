import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Company {
	
	private ArrayList<Employee> allEmployees;
	private ArrayList<Team> allTeams;
	

	private static Company instance = new Company();
	
	private Company() {
		this.allEmployees=new ArrayList<>();
		this.allTeams=new ArrayList<>();
	}
	
	public static Company getInstance()
	{
		return instance;
	}
	
	
	public void listTeams()
	{
		Team.list(this.allTeams);
	}
	
	public Employee createEmployee(String n, int yle) throws ExEmployeeAlreadyExists, ExLeaveOutOfRange
	{
		if(Employee.searchEmployee(this.allEmployees,n)!=null)
			throw new ExEmployeeAlreadyExists();
		if(yle>300)
			throw new ExLeaveOutOfRange();
		Employee e=new Employee(n,yle);
		allEmployees.add(e);
		Collections.sort(this.allEmployees);
		return e;
	}
	
	public Employee findEmployee(String n) throws ExEmployeeNotFound
	{
		Employee e;
		e=Employee.searchEmployee(this.allEmployees,n);
		if(e==null)
			throw new ExEmployeeNotFound();
		else
			return e;
	}
	public Team findTeam(String n) throws ExTeamNotFound
	{
		Team t;
		t=Team.searchTeam(this.allTeams,n);
		if(t==null)
			throw new ExTeamNotFound();
		else
			return t;
	}
	
	public Team createTeam(String n,Employee e) throws ExTeamAlreadyExists, ExEmployeeNotFound
	{
		if(Team.searchTeam(this.allTeams,n)!=null)
			throw new ExTeamAlreadyExists();
		
		Team t = new Team(n,e);
		allTeams.add(t);
		Collections.sort(this.allTeams);
		return t; 
	}

	public void listEmployees()
	{
		Employee.list(this.allEmployees);
		
	}
	
	public void listTeamsAndMembers()
	{
		for(Team t:allTeams)
		{
			System.out.println(t.getName()+":");
			t.listTeamMembers();
			System.out.println();
		}
	}
	
	public void listLeaves()
	{
		for(Employee e:this.allEmployees)
		{
			System.out.println(String.format("%s:", e.getName()));
			e.listLeaves();
		}
	}

	public void removeEmployee(Employee e) {
		this.allEmployees.remove(e);
		
	}

	public void addEmployee(Employee e) {
		this.allEmployees.add(e);
		
	}

	public void removeTeam(Team t)
	{
		
		this.allTeams.remove(t);
		
	}
	public void addTeam(Team t) {
		this.allTeams.add(t);
		
	}

	public ArrayList<Role> getActingHeadRoles(Employee e) 
	{
		return Team.getActingHeadRoles(allTeams,e);
	}
}
