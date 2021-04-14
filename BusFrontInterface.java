import java.util.Scanner;

public class BusFrontInterface {
	
	public static final int shortestPathOption = 1;
	public static final int busStopSearchOption = 2;
	public static final int tripSearchOption = 3;
	public static final String yesOption = "yes";
	public static final String noOption = "no";
	
	public static void main(String[] args) {
		
		Scanner initialInput = new Scanner(System.in);
		Scanner shortestPathInput = new Scanner(System.in);
		Scanner busStopSearchInput = new Scanner(System.in);
		Scanner arrivalTimeInput = new Scanner(System.in);
		boolean quit = false;
		
		System.out.println("Welcome to the Bus Management System");
		
		while(!quit)	
		{
			System.out.println("\nDo you wish to exit the Bus Managment System?\nType \"yes\" to exit the Bus Management System or type \"no\" to continue.");
			String exitInput = initialInput.next();
			if (exitInput.equalsIgnoreCase(yesOption))
			{
				quit = true;
			}
			else if (exitInput.equalsIgnoreCase(noOption))
			{
				System.out.println("\nPlease type:\n\"1\" to find the shortest path between two bus stops.\n\"2\" to serach for a bus stop.\n\"3\" to search for a trip with a given arrival time.");
				if (initialInput.hasNextInt())
				{
					int optionInput = initialInput.nextInt();
					if (optionInput == shortestPathOption)
					{
						boolean error = true;
						int firstBusStop = 0;
						int secondBusStop = 0;
						System.out.println("Find shortest Path between two Bus Stops.");
						while(error)
						{
							System.out.print("Type the name of the 1st Bus Stop here: ");
							if (shortestPathInput.hasNextInt())
							{
								firstBusStop = shortestPathInput.nextInt();
								System.out.print("\nType the name of the 2nd Bus Stop here: ");
								secondBusStop = shortestPathInput.nextInt();
								error = false;
							}
							else
							{
								System.out.println("Error! Please enter a valid integer value for the bus stop.");
								shortestPathInput.next();
							}
						}
						BusShortestPath sp = new BusShortestPath("stop_times.txt", firstBusStop, secondBusStop);
					}
					else if (optionInput == busStopSearchOption)
					{
						System.out.println("Search for a Bus Stop by name.");
						System.out.print("Please type the name or partial name of a stop here: ");
						String busStopString = busStopSearchInput.next();
						
						//Output bus stop(s) here
					
					}
					else if (optionInput == tripSearchOption)
					{
						System.out.println("Search for a trip by arrival time.");
						System.out.print("Please type the arrival time of your trip here: ");
						String arrivalTime = arrivalTimeInput.next();
						
						//Output Trips here
						
					}
					else
					{
						System.out.print("Input is invalid. Please try again!");
						initialInput.next();
					}
					
				}
				else
				{
					System.out.println("Error! this input is invalid. Please type in a valid input.");
					initialInput.next();
				}
			}
			else
			{
				System.out.println("Error! this input is invalid. Please type in a valid input.");
			}	
		}	
		System.out.println("You have exited the Bus Management System. Goodbye!");
		initialInput.close();
		shortestPathInput.close();
		busStopSearchInput.close();
		arrivalTimeInput.close();
		
		//BusSortSearchTrips stops = new BusSortSearchTrips("stop_times.txt");
	}
}
