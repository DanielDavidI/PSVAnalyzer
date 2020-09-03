package psvanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mainfile 
{

	public static void main(String[] args) 
	{
		System.out.println("Welcome to the PSV system analyzer. Please note that this program is not feature complete and is not ready to be relied upon");
		System.out.println("\nCurrently this program is intended to identify the applicable scenarios for a defined system");
		
		PSV dummy=new PSV();//this is a dummy object used to obtain the path
		String path=dummy.getClass().getResource("").getPath();
		Scanner keyboard=new Scanner(System.in);
	
		ArrayList<PSVsystem> systems= new ArrayList<PSVsystem>();
		try 
		{
			File PSVreader = new File (path+"data.dat");
			FileInputStream fromFile=new FileInputStream(PSVreader);
			ObjectInputStream ObjectLoader=new ObjectInputStream(fromFile);
					
			try 
			{		
				while(true) 
				{
					PSVsystem ReadSystem=(PSVsystem) ObjectLoader.readObject();
					systems.add(ReadSystem);
				}
			}
			catch(Exception e)
			{
				System.out.println(systems.size()+" entries read before reaching end of file");
			}
			ObjectLoader.close();
		}
		catch(Exception e)
		{
			System.out.println("Did not find \"data.dat\". A blank file will be generated and must be saved when completed\n"+e.getMessage());
		}
		 
		
		
		while(true)
		{
			System.out.println("main menu");
			System.out.println("1. List all systems in database");
			System.out.println("2. View system");
			System.out.println("3. Add System");
			System.out.println("4. Delete System");
			System.out.println("5. Save changes");
			System.out.println("6. Exit");
			int choice=0;
			
			
			try 
			{	
				choice=keyboard.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("error in selection "+e.getMessage());
			}
			if(choice==1)
			{
				System.out.println("Here is a list of all entered systems");
				for(PSVsystem s:systems)
				{
					System.out.println(s.name);
				}
				
			}
			if(choice==2)
			{
				System.out.println("Enter the name of your selection:");
				String name="";
				
				try {
				name=keyboard.next();
				}
				catch(Exception e)
				{
					
				}
				
				PSVsystem chosenSystem=null;
				
				for(PSVsystem s:systems)
				{
					if(s.name.compareTo(name)==0)
						chosenSystem=s;
				}
				if(chosenSystem==null)
				{
					System.out.println("error system could not be found");
				}
				else
				{
					while(true)
					{
						System.out.println("Here is system information:");
						System.out.println(chosenSystem.name);
						System.out.println("Is Liquid-Full? "+chosenSystem.liquidFull);
						System.out.println("Is Vapor-Full? "+chosenSystem.vaporFull);
						System.out.println("Has Cooling? "+chosenSystem.hasCooling);
						System.out.println("Has Heating? "+chosenSystem.hasHeatInput);
						System.out.println("Is a distillation tower "+chosenSystem.IsDistillationTower);
						System.out.println("Has separate liquid and Vapor Outlets "+chosenSystem.name);
						
						System.out.println();
						
						for(PSV p:chosenSystem.protectingPSVs)
						{
							System.out.println(p);
						}
						
						System.out.println();
						
						for(Equipment e:chosenSystem.protectedEquipment)
						{
							System.out.println(e);
						}
						
						System.out.println();
						
						for(OPsources o: chosenSystem.relevantEquipment)
						{
							System.out.println(o);
						}
						
						System.out.println("\n\n\n");
						
						System.out.println("what would you like to do?");
						System.out.println("1. main menu");
						System.out.println("2. identify scenarios");
						System.out.println("3. add PSV");
						System.out.println("4. edit PSV");
						System.out.println("5. remove PSV");
						System.out.println("6. add equipment");
						System.out.println("7. edit equipment");
						System.out.println("8. remove equipment");
						System.out.println("9. add relevant equipment");
						System.out.println("10. edit relevant equipment");
						System.out.println("11. remove relevant equipment");
						System.out.println("\n\n\n");
											
						try {
							choice=keyboard.nextInt();
						}
						catch(Exception e)
						{
							System.out.println("invalid input");
						}
						
						if(choice==1)
						{
							break;
						}
						else if(choice==2)
						{
							chosenSystem.printAnalysis();
						}
						else if(choice==3)
						{
							chosenSystem.protectingPSVs.add(new PSV());
						}
						else if(choice==4)
						{
							try 
							{
								System.out.println("which PSV in the list do you wish to access? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.protectingPSVs.get(subchoice-1).setValues(keyboard);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}
						}
						else if(choice==5)
						{
							try 
							{
								System.out.println("which PSV in the list do you wish to remove? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.protectingPSVs.remove(subchoice-1);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}

						}
						else if(choice==6)
						{
							chosenSystem.protectedEquipment.add(new Equipment(keyboard));
						}
						else if(choice==7)
						{
							try
							{
								System.out.println("which protected equipment in the list do you wish to access? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.protectedEquipment.get(subchoice-1).setValues(keyboard);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}
						}
						else if(choice==8)
						{
							try
							{
								System.out.println("which protected equipment in the list do you wish to remove? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.protectedEquipment.remove(subchoice-1);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}
						}
						else if(choice==9)
						{
							chosenSystem.relevantEquipment.add(new OPsources(keyboard));
						}
						else if(choice==10)
						{

							try
							{
								System.out.println("which relevant equipment in the list do you wish to access? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.relevantEquipment.get(subchoice-1).setValues(keyboard);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}
						}
						else if(choice==11)
						{
							try
							{
								System.out.println("which relevant equipment in the list do you wish to remove? 1 for the first, 2 for the second, etc");
								int subchoice=keyboard.nextInt();
								chosenSystem.relevantEquipment.get(subchoice-1).setValues(keyboard);
							}
							catch(Exception e)
							{
								System.out.println("invalid input");
							}
						}
					}
				}
			}
			if(choice==3)
			{
				systems.add(new PSVsystem(keyboard));
			}
			if(choice==4)
			{
				try 
				{
					System.out.println("which system would you like to remove (this is done by list order on the list all systems in the database)");
					int subchoice= keyboard.nextInt();
					systems.remove(subchoice-1);
				}
				catch(Exception e)
				{
					System.out.println("error");
				}
			}
			if(choice==5)
			{
				//todo
			}
			if(choice==6)
			{
				break;
			}
		}
		keyboard.close();
		System.exit(0);
	}

}
