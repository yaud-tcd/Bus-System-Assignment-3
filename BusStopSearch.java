import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BusStopSearch {
	
	private ArrayList<Integer> arrayOfStopIDs;
	private ArrayList<Stop> arrayOfStops;
	private ArrayList<Stop> arrayOfSortedStops;
	
	char data;
    boolean isEnd;
    TSTNode left, middle, right;
    TST searchTree;
				
	public BusStopSearch() throws FileNotFoundException
	{
		File stops = new File("stops.txt");
		Scanner stopsScanner = new Scanner(stops);
		
		arrayOfStopIDs = new ArrayList<Integer>();
		arrayOfStops = new ArrayList<Stop>();
		arrayOfSortedStops = new ArrayList<Stop>();
		
		int line = 0;
		
		//Create stops
		
		while(stopsScanner.hasNextLine())
		{
			Stop stop;
			String [] lineElements = stopsScanner.nextLine().trim().split(",");
			
			if (line > 0)
			{	
				stop = new Stop();
				stop.setName(lineElements[2]);
				stop.setStopID(Integer.parseInt(lineElements[0]));
				arrayOfStops.add(stop);
				arrayOfStopIDs.add(stop.getStopID());
				arrayOfSortedStops.add(stop);
			}
			line++;
		}
		stopsScanner.close(); 
		
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
		
		File stopTimes = new File("stop_times.txt");
		Scanner stopTimesScanner = new Scanner(stopTimes);
		
		line = 0;
		
		while(stopTimesScanner.hasNextLine())
		{
			Stop currentStop = null;
			String [] lineElements = stopTimesScanner.nextLine().trim().split(",");
			
			if (line > 0)
			{	
				int currentStopID = Integer.parseInt(lineElements[3]);
				int currentStopIndex = binarySearch(arrayOfStopIDs, currentStopID);
				currentStop = arrayOfSortedStops.get(currentStopIndex);
				currentStop.addTripID(Integer.parseInt(lineElements[0]));
			}
			line++;
		}
		stopTimesScanner.close();
		
		for (int j = 0; j < 2; j++)
		{
		    for (int i = 0; i < arrayOfStops.size(); i++)
		    {
		    	String currentStopName = arrayOfStops.get(i).getName();
	    		if (currentStopName.startsWith("WB"))
		    	{
			    	currentStopName = currentStopName.replace("WB ","");
				    arrayOfStops.get(i).setName(currentStopName + " WB");
	     		}
		    	else if (currentStopName.startsWith("EB"))
		      	{
			    	currentStopName = currentStopName.replace("EB ","");
			    	arrayOfStops.get(i).setName(currentStopName + " EB");
			    }
			    else if (currentStopName.startsWith("NB"))
			    {
			    	currentStopName = currentStopName.replace("NB ","");
			    	arrayOfStops.get(i).setName(currentStopName + " NB");
		    	}
		    	else if (currentStopName.startsWith("SB"))
		    	{
		    		currentStopName = currentStopName.replace("SB ","");
		    		arrayOfStops.get(i).setName(currentStopName + " SB");
		    	}
    			else if (currentStopName.startsWith("FLAGSTOP"))
     			{
    				currentStopName = currentStopName.replace("FLAGSTOP ","");
    				arrayOfStops.get(i).setName(currentStopName + " FLAGSTOP");
    			}
    		}
    	}
		
        searchTree = new TST();
		
		for(int k = 0; k < arrayOfStops.size(); k++)
		{
			String key = arrayOfStops.get(k).getName();
			searchTree.insert(key);
		}
	}
	
	public String outputToString(String userInput) 
	{
		String output = null;
		String open = null;

		String searchStop = userInput;
	    String desiredStop = searchStop.toUpperCase();

	    open = "\nSearching for all stops from term: " + userInput + "\n";
	    output = searchTree.searchForStop(desiredStop, arrayOfStops);

	    return open + output;
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
