package tetris.model;

public class Player implements Comparable
{
	private String name;
	private int score;
	
	public Player()
	{
		
	}
	
	public Player(String name, int score)
	{
		this.name = name;
		this.score = score;
	}
	
	@Override
	public int compareTo(Object object)
	{
		if(object instanceof Player)
		{
			return((Player)object).score-this.score;
		}
		else
		{
			return -1;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
}
