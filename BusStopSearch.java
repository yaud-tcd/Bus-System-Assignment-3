import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopSearch {
	
	private static ArrayList<Stop> arrayOfStops;
	char data;
    boolean isEnd;
    TSTNode left, middle, right;
    TST searchTree;
				
	public BusStopSearch() throws FileNotFoundException
	{
		File stops = new File("stops.txt");
		Scanner stopsScanner = new Scanner(stops);
		
		arrayOfStops = new ArrayList<Stop>(0);
		
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
			}
			line++;
		}
		stopsScanner.close(); 
		
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
}
