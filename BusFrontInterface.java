import java.util.Scanner;
import java.io.IOException;

public class BusFrontInterface {
	
	public static final int shortestPathOption = 1;
	public static final int busStopSearchOption = 2;
	public static final int tripSearchOption = 3;
	public static final String yesOption = "yes";
	public static final String noOption = "no";
	
	public static void main(String[] args) throws IOException {
		
		Scanner initialInput = new Scanner(System.in);
		Scanner shortestPathInput = new Scanner(System.in);
		Scanner busStopSearchInput = new Scanner(System.in);
		Scanner arrivalTimeInput = new Scanner(System.in);
		boolean quit = false;
		
		System.out.println("Welcome to the Bus Management System");
		
		while(!quit)	
		{
			System.out.println("\nDo you wish to exit the Bus Management System?\nType \"yes\" to exit the Bus Management System or type \"no\" to continue.");
			String exitInput = initialInput.next();
			if (exitInput.equalsIgnoreCase(yesOption))
			{
				quit = true;
			}
			else if (exitInput.equalsIgnoreCase(noOption))
			{
				boolean choiceError = true;
				while(choiceError)
				{
					System.out.println("\nPlease type:\n\"1\" to find the shortest path between two bus stops.\n\"2\" to search for a bus stop.\n\"3\" to search for a trip with a given arrival time.");
					if (initialInput.hasNextInt())
					{
						int optionInput = initialInput.nextInt();
						if (optionInput == shortestPathOption)  //If user chooses shortest path option
						{
							boolean firstStopError = true;
							boolean secondStopError = true;
							int firstBusStop = 0;
							int secondBusStop = 0;
							System.out.println("Find shortest Path between two Bus Stops.\n");
							while(firstStopError)
							{
								System.out.print("Type the integer Stop ID of the Source Bus Stop here: ");
								if (shortestPathInput.hasNextInt())
								{
									firstBusStop = shortestPathInput.nextInt();
									while(secondStopError)
									{
										System.out.print("\nType the integer Stop ID of the Destination Bus Stop here: ");
										if (shortestPathInput.hasNextInt())
										{
											secondBusStop = shortestPathInput.nextInt();
											BusShortestPath sp = new BusShortestPath();
											System.out.print(sp.outputToString(firstBusStop, secondBusStop));
											secondStopError = false;
											
										}
										else
										{
											System.out.println("Error! Please enter a valid integer value for the bus stop.");
											shortestPathInput.next();
										}
									}
									firstStopError = false;
								}
								else
								{
									System.out.println("Error! Please enter a valid integer value for the bus stop.");
									shortestPathInput.next();
								}
							}
							choiceError = false;
						}
						else if (optionInput == busStopSearchOption) //If user chooses stop search option
						{
							System.out.println("Search for a Bus Stop by name.");
							System.out.print("Please type the name or partial name of a stop here: ");
							String busStopString = busStopSearchInput.next();
							
							BusStopSearch bss = new BusStopSearch();
							System.out.print(bss.outputToString(busStopString));
							
							choiceError = false;
						}
						else if (optionInput == tripSearchOption) //If user chooses trip search option
						{
							System.out.println("Search for a trip by arrival time.");
							System.out.print("Please type the arrival time of your trip here: ");
							String arrivalTime = arrivalTimeInput.next();
							
							ArrivalTimeSearch ats = new ArrivalTimeSearch();
							System.out.print(ats.outputToString(arrivalTime));
							
							choiceError = false;
						}
						else
						{
							System.out.println("Input is invalid. Please try again!");
							//initialInput.next();
						}	
					}
					else
					{
						System.out.println("Error! this input is invalid. Please type in a valid input.");
						initialInput.next();
					}
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
		
	}
}
