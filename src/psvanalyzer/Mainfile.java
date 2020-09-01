package psvanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mainfile 
{

	public static void main(String[] args) 
	{
		System.out.println("Welcome to the PSV system analyzer. Please note that this program is not feature complete and is not ready to be relied upon");
		
		PSV dummy=new PSV();//this is a dummy object used to obtain the path
		String path=dummy.getClass().getResource("").getPath();
		Scanner keyboard=new Scanner(System.in);
	
		ArrayList<PSVsystem> systems= new ArrayList<PSVsystem>();
		try 
		{
			File PSVreader = new File (path+"data.csv");
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
			System.out.println("Did not find \"data.csv\". A blank file will be generated and must be saved when completed\n"+e.getMessage());
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
						
						/*for()
						{
							
						}
						for()
						{
							
						}
						for()*/
						
					}
				}
			}
			if(choice==3)
			{
				//todo
			}
			if(choice==4)
			{
				//todo
			}
			if(choice==5)
			{
				//todo
			}
			if(choice==6)
			{
				System.exit(0);
			}
		}
	}

}
