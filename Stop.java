import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Stop {

	private String name;
	private int stopID;
	
	private ArrayList<Stop> shortestPath = new ArrayList<>();
	private int distTo = Integer.MAX_VALUE;
	
	Map<Stop, Integer> adjacentStops = new HashMap<>();
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setStopID(int stopID)
	{
		this.stopID = stopID;
	}
	
	public int getStopID() 
	{
		return stopID;
	}
	
	public void setDistTo(int distTo)
	{
		this.distTo = distTo;
	}
	
	public int getDistTo()
	{
		return distTo;
	}
	
	public void setShortestPath(ArrayList<Stop> shortestPath)
	{
		this.shortestPath = shortestPath;
	}
	
	public ArrayList<Stop> getShortestPath()
	{
		return shortestPath;
	}
	
	public void addEdge(Stop nextStop, int edgeWeight)
	{
		adjacentStops.put(nextStop, edgeWeight);
	}
	
	public Map<Stop, Integer> getAdjacentStops()
	{
		return adjacentStops;
	}
}
