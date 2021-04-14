import java.io.File;
import java.util.Scanner;


public class BusShortestPath {
	
	public String fileName;
	public int firstStopID;
	public int secondStopID;
	public int edgeTo[][];
	public double distTo[][];
	
	
	
	BusShortestPath(String fileName, int firstStopID, int secondStopID)
	{
		try
		{
			File stopTimesFile = new File(fileName);
			Scanner stopTimesFileScanner = new Scanner(stopTimesFile);
			
			int lineNumber = 0;
			
			int firstStopTripID = -1;
			int secondStopTripID = -1;
			
			int firstStop = -1;
			int secondStop = -1;

			while(stopTimesFileScanner.hasNextLine())
			{	
				String [] stopTimesLineElements = stopTimesFileScanner.nextLine().trim().split(",");
				
				if (lineNumber > 0)
				{
					if ()
					
					
				}
				lineNumber++;
			}
			System.out.println(firstStop);
			System.out.println(firstStopLine);
			System.out.println(secondStop);
			System.out.println(secondStopLine);
			
			stopTimesFileScanner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean isInSameTrip(String fileName, int firstStopID, int secondStopID)
	{
		boolean isInSameTrip = false;
		try
		{
			File stopTimesFile = new File(fileName);
			Scanner stopTimesFileScanner = new Scanner(stopTimesFile);
			
			int lineNumber = 0;
			
			int firstTripID = 0;
			int secondTripID = 0;
			
			String [] stopTimesLineElements = stopTimesFileScanner.nextLine().trim().split(",");
			
			while(stopTimesFileScanner.hasNextLine())
			{
				if (Integer.parseInt(stopTimesLineElements[3]) == firstStopID)
				{
					firstTripID = Integer.parseInt(stopTimesLineElements[0]);
				}
				else if(Integer.parseInt(stopTimesLineElements[3]) == secondStopID)
				{
					secondTripID = Integer.parseInt(stopTimesLineElements[0]);
				}
				if(firstTripID == secondTripID)
				{
					isInSameTrip = true;
					break;
				}
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return isInSameTrip;
	}
	
	public void Output()
	{
		
	}
	
	
	

}
