package psvanalyzer;

import java.io.Serializable;
import java.util.ArrayList;

public class PSVsystem implements Serializable
{
	String name;
	ArrayList<PSV> protectingPSVs;
	ArrayList<Equipment> protectedEquipment;
	ArrayList<OPsources>  credableScenarios;
	
	boolean liquidFull;
	boolean vaporFull;
	boolean IsDistillationTower;
	boolean hasOutletsWithDifferentPhases;
	boolean hasHeatInput;
	boolean hasCooling;
	
	public PSVsystem()
	{
		protectingPSVs=new ArrayList<PSV>();
		protectedEquipment=new ArrayList<Equipment>();
		credableScenarios=new ArrayList<OPsources>();
		
		liquidFull=false;
		vaporFull=false;
		IsDistillationTower=false;
		hasOutletsWithDifferentPhases=false;
		hasHeatInput=false;
		hasCooling=false;
	}
	
}
