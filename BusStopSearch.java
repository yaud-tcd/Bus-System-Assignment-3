import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopSearch {
	
	private static ArrayList<String> arrayOfStops;
	char data;
    boolean isEnd;
    TSTNode left, middle, right;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		createStops();
		sortTST();
	}
			

			
	private static void createStops() throws FileNotFoundException
	{
		File stops = new File("stops.txt");
		Scanner stopsScanner = new Scanner(stops);
		
		arrayOfStops = new ArrayList<String>(0);
		
		int line = 0;
		
		//Create stops
		
		while(stopsScanner.hasNextLine())
		{
			String stop;
			String [] lineElements = stopsScanner.nextLine().trim().split(",");
			
			if (line > 0)
			{	
				stop = lineElements[2];
				arrayOfStops.add(stop);
			}
			line++;
		}
		stopsScanner.close(); 
		
		for (int j = 0; j < 2; j++)
		{
		for (int i = 0; i < arrayOfStops.size(); i++){
			String currentStop = arrayOfStops.get(i);
			if (currentStop.startsWith("WB"))
			{
				currentStop = currentStop.replace("WB ","");
				arrayOfStops.set(i,currentStop + " WB");
			}
			else if (currentStop.startsWith("EB"))
			{
				currentStop = currentStop.replace("EB ","");
				arrayOfStops.set(i,currentStop + " EB");
			}
			else if (currentStop.startsWith("NB"))
			{
				currentStop = currentStop.replace("NB ","");
				arrayOfStops.set(i,currentStop + " NB");
			}
			else if (currentStop.startsWith("SB"))
			{
				currentStop = currentStop.replace("SB ","");
				arrayOfStops.set(i,currentStop + " SB");
			}
			else if (currentStop.startsWith("FLAGSTOP"))
			{
				currentStop = currentStop.replace("FLAGSTOP ","");
				arrayOfStops.set(i,currentStop + " FLAGSTOP");
			}
			
		}
	}
	}
	
	private static void sortTST() {
		TST searchTree = new TST();
		
		for( int k = 0; k < arrayOfStops.size(); k++)
		{
			String key = arrayOfStops.get(k);
			searchTree.insert(key);
		}
	int k = 0;
	while (k < 1)
	{
	Scanner userStop = new Scanner(System.in);
	System.out.println("\nEnter address of Bus stop(or 'Done' to cancel):");
	
	String searchStop = userStop.nextLine();
	String desierdStop = searchStop.toUpperCase();
	if (desierdStop.contains("DONE"))
	{
		k = k + 1;
		System.out.print("Thank you for using our services.");
	}
	else
	{
		System.out.print(searchTree.searchForStop(desierdStop));
		if (searchTree.searchForStop(desierdStop) == "")
		{
			System.out.println("No Stops matching this search term.");
		}
	}
	}
	}
}
	 
	
