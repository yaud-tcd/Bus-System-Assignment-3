import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Stop {

	private String name;
	private int stopID;
	
	private ArrayList<Stop> shortestPath = new ArrayList<>();
	private int distTo = Integer.MAX_VALUE;
	
	private ArrayList<Integer> tripIDs = new ArrayList<Integer>();
	
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
	
	public void addTripID(int tripID)
	{
		this.tripIDs.add(tripID);
	}
	
	public String outputTripIDs()
	{
		String output = tripIDs.get(0).toString();
		for (int i = 1; i < tripIDs.size(); i++)
		{
			output = output + ", " + tripIDs.get(i).toString();
		}
		return output;
	}
}
