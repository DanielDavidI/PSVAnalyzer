package psvanalyzer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class PSVsystem implements Serializable
{
	String name;
	ArrayList<PSV> protectingPSVs;
	ArrayList<Equipment> protectedEquipment;
	ArrayList<OPsources>  relevantEquipment;
	
	boolean liquidFull;
	boolean vaporFull;
	boolean IsDistillationTower;
	boolean hasOutletsWithDifferentPhases;
	boolean hasHeatInput;
	boolean hasCooling;
	
	public PSVsystem(Scanner k)
	{
		protectingPSVs=new ArrayList<PSV>();
		protectedEquipment=new ArrayList<Equipment>();
		relevantEquipment=new ArrayList<OPsources>();
		
		liquidFull=false;
		vaporFull=false;
		IsDistillationTower=false;
		hasOutletsWithDifferentPhases=false;
		hasHeatInput=false;
		hasCooling=false;
		
		this.setValues(k);
	}
	public void setValues(Scanner k)
	{
		try
		{
			System.out.println("please enter system name:");
			name=k.next();
			
			System.out.println("Is the system liquid-full? (y/n)");
			String choice=k.next();
			if(choice.equals("y"))
				liquidFull=true;
			else if(choice.equals("n"))
				liquidFull=false;
			else
				System.out.println("Invalid input: defaulting to false");
			
			System.out.println("Is the system vapor-full (y/n)");
			choice=k.next();
			if(choice.equals("y"))
				vaporFull=true;
			else if(choice.equals("n"))
				vaporFull= false;
			else
				System.out.println("Invalid input: defaulting to false");
			System.out.println("Is the system a distillation column with a reboiler and condenser? (y/n)");
			choice=k.next();
			if(choice.equals("y"))
				IsDistillationTower=true;
			else if(choice.equals("n"))
				IsDistillationTower=false;
			else
				System.out.println("Invalid input: defaulting to false");
			System.out.println("Does the system have outputs of different phases? (vapor/liquid?) (y/n)");
			choice=k.next();
			if(choice.equals("y"))
				hasOutletsWithDifferentPhases=true;
			else if(choice.equals("n"))
				hasOutletsWithDifferentPhases=false;
			else
				System.out.println("Invalid input: defaulting to false");
			System.out.println("Is there heat input? (y/n)");
			choice=k.next();
			if(choice.equals("y"))
				hasHeatInput=true;
			else if(choice.equals("n"))
				hasHeatInput=false;
			else
				System.out.println("Invalid input: defaulting to false");
			
			System.out.println("Is there cooling present? (y/n)");
			choice=k.next();
			if(choice.equals("y"))
				hasCooling=true;
			else if(choice.equals("n"))
				hasCooling=false;
			else
				System.out.println("Invalid input: defaulting to false");
			
			
		}
		catch(Exception e)
		{
			System.out.println();
		}
	}
	public void printAnalysis()
	{
		return;//todo
	}
	
}
