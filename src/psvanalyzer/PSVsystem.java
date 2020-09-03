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
			System.out.println("What is the PSV tag");
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
