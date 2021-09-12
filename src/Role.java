
public abstract class Role implements Comparable<Role>
{
	
	private Team t;
	
	public Role(Team aT)
	{
		this.t=aT;
	}
	
	public abstract String genMessage();
	
	public String getTeamName()
	{
		return t.getName();
	}
	public Team getTeam()
	{
		return t;
	}
	
	@Override
	public int compareTo(Role another)
	{
		return this.t.compareTo(another.t);
	}

}
