import java.io.File;
import java.util.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

public class BusShortestPath {
	
	private ArrayList<Integer> arrayOfStopIDs;
	private ArrayList<Stop> arrayOfStops;
	private ArrayList<Stop> arrayOfSortedStops;
	
	private Graph busSystem;
	
	private final int STOP_TIMES_COST = 1;
	private final int TRANSFERS_TYPE_ZERO_COST = 2;
	
	private final int TRANSFERS_TYPE_ZERO = 0;
	private final int TRANSFERS_TYPE_TWO = 2;
	
	private final int INFINITY = Integer.MAX_VALUE;
	
	BusShortestPath()
	{
		try
		{
			File stopTimes = new File ("stop_times.txt");
			Scanner stopTimesScanner = new Scanner(stopTimes);
			
			File stops = new File("stops.txt");
			Scanner stopsScanner = new Scanner(stops);
			
			File transfers = new File("transfers.txt");
			Scanner transfersScanner = new Scanner(transfers);
			
			arrayOfStopIDs = new ArrayList<Integer>(0);
			arrayOfStops = new ArrayList<Stop>(0);
			arrayOfSortedStops=  new ArrayList<Stop>(0);
			
			int line = 0;
			
			busSystem = new Graph();
			
			//Create stops
			
			while(stopsScanner.hasNextLine())
			{
				Stop stop;
				String [] lineElements = stopsScanner.nextLine().trim().split(",");
				
				if (line > 0)
				{	
					stop = new Stop();
					stop.setStopID(Integer.parseInt(lineElements[0]));
					stop.setName(lineElements[2]);
					busSystem.addStop(stop);
					arrayOfStops.add(stop);
					arrayOfSortedStops.add(stop);
					arrayOfStopIDs.add(stop.getStopID());
				}
				line++;
			}
			stopsScanner.close();

			line = 0;
			
			//Sort Stop ID arrays and Stops array
			
			Collections.sort(arrayOfStopIDs);
			
			for(int i = 0; i < arrayOfStops.size(); i++)
			{
				for (int j = 0; j < arrayOfStops.size(); j++)
				{
					if (arrayOfStops.get(j).getStopID() == arrayOfStopIDs.get(i))
					{
						arrayOfSortedStops.set(i, arrayOfStops.get(j));
						break;
					}
				}
			}
			
			//Create edges from stop_times.txt
			
			int previousTripID = -1;
			int previousStopID = -1;
			
			Stop currentStop = null;
			Stop previousStop = null;

			while(stopTimesScanner.hasNextLine())
			{	
				String[] lineElements = stopTimesScanner.nextLine().trim().split(",");
				
				if (line > 0)
				{
					int currentStopID = Integer.parseInt(lineElements[3]);
					int currentStopIndex = binarySearch(arrayOfStopIDs, currentStopID);
					currentStop = arrayOfSortedStops.get(currentStopIndex);
					
					if (previousTripID == Integer.parseInt(lineElements[0]))
					{
						previousStop.addEdge(currentStop, STOP_TIMES_COST);
					}
					previousTripID = Integer.parseInt(lineElements[0]);
					previousStopID = Integer.parseInt(lineElements[3]);
					
					int previousStopIndex = binarySearch(arrayOfStopIDs, previousStopID);
					
					previousStop = arrayOfSortedStops.get(previousStopIndex);
				}
				line++;
			}
			stopTimesScanner.close();

			//Create edges from transfers.txt
			
			line = 0;

			while(transfersScanner.hasNextLine())
			{
				String[] lineElements = transfersScanner.nextLine().trim().split(",");
				if(line > 0)
				{
					int fromStopID = Integer.parseInt(lineElements[0]);
					int toStopID = Integer.parseInt(lineElements[1]);
					
					int fromStopIndex = binarySearch(arrayOfStopIDs, fromStopID);
					int toStopIndex = binarySearch(arrayOfStopIDs, toStopID);
					
					Stop toStop = arrayOfSortedStops.get(toStopIndex);
					Stop fromStop = arrayOfSortedStops.get(fromStopIndex);
					
					int transferType = Integer.parseInt(lineElements[2]);
					
					if (transferType == TRANSFERS_TYPE_ZERO)
					{
						fromStop.addEdge(toStop, TRANSFERS_TYPE_ZERO_COST);
					}
					else if (transferType == TRANSFERS_TYPE_TWO)
					{
						int edgeCost = Integer.parseInt(lineElements[3])/100;
						fromStop.addEdge(toStop, edgeCost);
					}
				}
				line++;
			}
			transfersScanner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public void shortestPath(int sourceStopID, int destinationStopID, Graph busSystem)
	{	
		int sourceStopIndex = binarySearch(arrayOfStopIDs, sourceStopID);
		int destinationStopIndex = binarySearch(arrayOfStopIDs, destinationStopID);
		
		Stop sourceStop = arrayOfSortedStops.get(sourceStopIndex);
		Stop destinationStop = arrayOfSortedStops.get(destinationStopIndex);
		
		sourceStop.setDistTo(0);
		
		Set<Stop> settledStops = new HashSet<Stop>();
		Set<Stop> unsettledStops = new HashSet<Stop>();

		unsettledStops.add(sourceStop);
		
		while (unsettledStops.size() != 0)
		{
			Stop currentStop = getLowestDistanceStop(unsettledStops);
			unsettledStops.remove(currentStop);
			for (Map.Entry<Stop, Integer> adjacencyPair : currentStop.getAdjacentStops().entrySet())
			{
				Stop adjacentStop = adjacencyPair.getKey();
				int edgeWeight = adjacencyPair.getValue();
				if (!settledStops.contains(adjacentStop))
				{
					calculateMinimumDistance(adjacentStop, edgeWeight, currentStop);
					unsettledStops.add(adjacentStop);
				}
			}
			settledStops.add(currentStop);
			if (currentStop == destinationStop)
			{
				break;
			}
		}
	}
	
	private Stop getLowestDistanceStop(Set<Stop> unsettledStops)
	{
		Stop lowestDistanceStop = null;
		int lowestDistance = INFINITY;
		for (Stop stop: unsettledStops)
		{
			int stopDistance = stop.getDistTo();
			if (stopDistance < lowestDistance)
			{
				lowestDistance = stopDistance;
				lowestDistanceStop = stop;
			}	
		}
		return lowestDistanceStop;
	}
	
	private void calculateMinimumDistance(Stop evaluationStop, int edgeWeight, Stop sourceStop)
	{
		int sourceDistance = sourceStop.getDistTo();
		if (sourceDistance + edgeWeight < evaluationStop.getDistTo())
		{
			evaluationStop.setDistTo(sourceDistance + edgeWeight);
			ArrayList<Stop> shortestPath = new ArrayList<Stop>(sourceStop.getShortestPath());
			shortestPath.add(sourceStop);
			evaluationStop.setShortestPath(shortestPath);
		}
	}

	public String outputToString(int sourceStopID, int destinationStopID)
	{
		String output = null;
		String open = null;
		String end = null;
		int sourceStopIndex = binarySearch(arrayOfStopIDs, sourceStopID);
		int destinationStopIndex = binarySearch(arrayOfStopIDs, destinationStopID);

		if(sourceStopIndex == -1 && destinationStopIndex == -1)
		{
			output = "\nError! Both the Source Stop ID (" + sourceStopID +") and Destination Stop ID (" + destinationStopID + ") are invalid. Please try again.\n";
			return output;
		}
		else if(sourceStopIndex == -1 && destinationStopIndex != -1)
		{
			output = "\nError! The Source Stop ID (" + sourceStopID +") is invalid. Please try again.\n";
			return output;
		}
		else if(destinationStopIndex == -1 && sourceStopIndex != -1)
		{
			output = "\nError! The Destination Stop ID (" + destinationStopID + ") is invalid. Please try again.\n";
			return output;
		}
		else if(destinationStopIndex == sourceStopIndex)
		{
			output = "\nError! The Source Stop ID and the Destination Stop ID are the same. Please try again.\n";
			return output;
		}
		else
		{	
			shortestPath(sourceStopID, destinationStopID, busSystem);

			open = "\nShortest Path From " + arrayOfSortedStops.get(sourceStopIndex).getName() + " to " + arrayOfSortedStops.get(destinationStopIndex).getName() + ".\n";
			output = "\nSource Stop:\nStop Name: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(0).getName() + "\nStop ID: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(0).getStopID() + "\nCost from Previous Stop: N/A" + "\nCost From Source: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(0).getDistTo() + "\n";
			for (int j = 1; j < arrayOfSortedStops.get(destinationStopIndex).getShortestPath().size(); j++)
			{	
				output = output + "\nStop " + j + ":\nStop Name: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(j).getName() + "\nStop ID: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(j).getStopID() + "\nCost From Previous Stop: " + (arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(j).getDistTo() - arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(j-1).getDistTo()) + "\nCost From Source: " + arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(j).getDistTo() + "\n";
			}
			output = output + "\nDestination Stop:\nStop Name: " + arrayOfSortedStops.get(destinationStopIndex).getName() + "\nStop ID: " + arrayOfSortedStops.get(destinationStopIndex).getStopID() + "\nCost from Previous Stop: " + (arrayOfSortedStops.get(destinationStopIndex).getDistTo() - arrayOfSortedStops.get(destinationStopIndex).getShortestPath().get(arrayOfSortedStops.get(destinationStopIndex).getShortestPath().size() - 1).getDistTo()) +"\nCost From Source: " + arrayOfSortedStops.get(destinationStopIndex).getDistTo() + "\n";
			end = "\nTotal Cost from Source to Destination: " + arrayOfSortedStops.get(destinationStopIndex).getDistTo() + "\n";
		}
		return open + output + end;
	}
	
	public int binarySearch(ArrayList<Integer> arrayOfStopIDs, int stopID)
	{
		int lo = 0, hi = arrayOfStopIDs.size()-1;
		while (lo <= hi) 
		{
			int mid = lo + (hi - lo)/2;
			if (stopID < arrayOfStopIDs.get(mid)) 
			{
				hi = mid - 1;
			}
			else if (stopID > arrayOfStopIDs.get(mid))
			{
				lo = mid + 1;
			}
			else 
			{
				return mid;
			}
		}
		return -1;
	}
}