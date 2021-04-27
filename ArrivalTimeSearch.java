
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ArrivalTimeSearch {
	
	
	public static ArrayList<String> searchArrivalTime( String arrivalTime ) throws IOException
	{
				
		//create array list for each line in the stop times file
		ArrayList<String> detailsOfBuses = new ArrayList<String>();
		
		//create array for the division of each piece of info in each line in the stop times file
		String [] splitLine = new String[0];

		
		//use try and catch in order to prevent file not found errors
		try
    	{
			
			//read in stop times file
			File file = new File("stop_times.txt");
			Scanner scanner = new Scanner(file);
			
			//skip line with title info
			scanner.nextLine();
			
			//create string variable for each line of info in stop times file
			String line;
			
			//create while loop to loop until the last line of the file
			while(scanner.hasNextLine())
			{
				
				line = scanner.nextLine();
				
				//split line into individual strings
				splitLine = line.split(",");
			    String arrivalTimeFromFile = splitLine[1];
			    
			    //split the arrival time into hours, minutes and seconds
			    String timeSplit[] = arrivalTimeFromFile.trim().split(":");
			    
			    String hours = timeSplit[0];
			    String minutes = timeSplit[1];
			    String seconds = timeSplit[2];
			    
			    int hh =  Integer.parseInt( hours ); 
			    int mm =  Integer.parseInt( minutes );
			    int ss =  Integer.parseInt( seconds );
			    
			    //test for invalid times
			    if( hh > 23 || hh < 0)
			    {
			    	line = null;	
			    }
			    
			    else if( mm > 59 || mm < 0 )
			    {
			    	line = null;
			    }
			    
			    else if( ss > 59 || ss < 0 )
			    {
			    	line = null;
			    }
			    
			 	//test if the input time is the same as the time in the stop times file    
			    if(  arrivalTimeFromFile.equals(arrivalTime)  )
			    {
			    	
			    	detailsOfBuses.add(line);
			    	
			    }
			    
			    //remove invalid times
			    else if(line == null)
			    {
			    	detailsOfBuses.remove(line);
			    }

				
			}
			
			scanner.close();
			
    	}
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		sort(detailsOfBuses);
		
		return detailsOfBuses;
		
		
	}
	
	
	
	
	
		public static void sort( ArrayList <String> array   )
		{
			//test for an empty array
			if( array.size() == 0)
			{
				return;
			}
			
			//convert from array list to array
			String [] busesToSort = new String[ array.size() - 1 ] ;
			
			for(int i = 0 ; i < array.size() - 1 ; i++)
			{
				
				busesToSort[i] = array.get(i);
	
			}
			
			//create array for bus ID numbers
			int [] busIDs = new int[ array.size() -1 ];
			
			//add ID numbers to the ID numbers array
			for( int j = 0 ; j < busesToSort.length ; j ++)
			{
				
				
				String [] busSplit =  busesToSort[j].split(",");
				
				int busID = Integer.parseInt(busSplit[0]);
				busIDs[j] = busID;
					
			}
			
			
			
			int temp;
			String temp2;
			
			
			//sorting ID numbers in ascending order and sorting the array of relevant buses accordingly
			for( int i = 0 ; i < busIDs.length ; i++)
			{
				for(int j = i ; j > 0 ; j--) 
				{
					if( busIDs[j] < busIDs[j-1] )
					{
						
						temp = busIDs[j];
						busIDs[j] = busIDs[j-1];
						busIDs[j-1] = temp;
						
						
						temp2 = array.get(j);
						array.set(j, array.get(j-1));
						array.set(j-1, temp2);

						
					}
				}
			}
				
			
			
		}
		
		/*
		
	
	public static void main(String[] args) throws IOException {
		
		

		ArrayList<String> result = searchArrivalTime( " 5:00:00" );
		
		if( result.isEmpty()  )
		{
			System.out.println("Invalid time or no bus at this time");
		}
		
		
		for( int i = 0 ; i < result.size() ; i++)
		{
			
			System.out.println(result.get(i));
			
		}
		
		
		
	}
	
	*/
	
}
