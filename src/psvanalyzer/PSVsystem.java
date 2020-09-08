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
		double SystemMAWP=0;//units of psig
		for (Equipment e: protectedEquipment)//This loop looks for the weakest piece of protected equipment
		{
			if(SystemMAWP==0||e.MAWP<SystemMAWP)
				SystemMAWP=e.MAWP;
		}
		
		double allowableOverpressure=0;
		
		
		double setPressure=0;
		
		for(PSV p:protectingPSVs)
		{
			if(p.setPressure>setPressure)
				setPressure=p.setPressure;
		}
		
		if(SystemMAWP>30)
		{
			if(protectingPSVs.size()==0)
				allowableOverpressure=1.1*SystemMAWP;
			else
				allowableOverpressure=1.16*SystemMAWP;
		}
		else
		{
			if(protectingPSVs.size()==0)
				allowableOverpressure=3+SystemMAWP;
			else
				allowableOverpressure=4+SystemMAWP;
		}
		
		
		
		if(SystemMAWP==0)
		{
			System.out.println("Error: no system MAWP data is provided. No meaningful analysis can be performed.");
			return;
		}
		if(protectingPSVs.isEmpty())
		{
			System.out.println("Error: no PSV is present in this system. No meanngful analysis can be performed.");
			return;
		}
		
		
		
		
		System.out.println("Closed Outlets:");
		for(OPsources r: relevantEquipment)
		{
			if(r.pressure>allowableOverpressure)
			{
				System.out.println(r.Description+" may result in overpressure as the upstream pressure, "+r.pressure+", exceeds the allowable overpressure of "+setPressure);
			}
			else if(r.pressure<allowableOverpressure&&r.pressure>SystemMAWP)
			{
				System.out.println(r.Description+" may result in overpressure as the upstream pressure, "+r.pressure+" exceeds the set pressure. However, the alloabale overpressure is not exceeded and the pressure relief devices are adequate by inspection.");
			}
			else
			{
				System.out.println(r.Description+" is not expected to result in overpressure as the upstream pressure "+r.pressure+" does not exceed the set pressure "+setPressure);
			}
		}
		System.out.println("Cooling failure");
		
		if(hasCooling)
		{
			System.out.println("Cooling failure may result in overpressure. Note that the nature of the cooling system is important in determing relief (if any). In some circumstances this may be referred to \"closed outlets\"");
		}
		else
		{
			System.out.println("No cooling system is identified to be in this system");
		}
		
		System.out.println("Top Tower Reflux Failure");
		
		if(IsDistillationTower)
		{
			System.out.println("Top tower reflux failure may result in overpressure as cooling is lost to the system. System modeling may be required to understand the relief requirements");
		}
		else
		{
			System.out.println("No top tower reflux is identified to be in this system.");
		}
		
		System.out.println("Sidestream reflux failure");
		
		if(IsDistillationTower)
		{
			System.out.println("Side stream reflux failure may result in overpressure as cooling is lost to the system. System modeling may be required to understand the relief requirements\nNote that this tool does not check for the presence of sidestream reflux. If it does not exist, disregard this message");
		}
		else
		{
			System.out.println("No top tower reflux is identified to be in this system.");
		}
		
		System.out.println("Sidestream reflux failure");
		System.out.println("This tool does not check for the presence of lean oil. If lean oil is present, relief requirements may be based on the increased vapor output associated with the lack of absorbtion");
		
		System.out.println("Accumulation of noncondensables");
		if(liquidFull)
		{
			System.out.println("Noncondensables were not identified to be present in this system.");
		}
		else
		{
			System.out.println("Note that this program does not differentiate between possible scenarios. In a closed outlet scenario, vapors may accumulate and lead to overpressure if the upstream pressure is higher than the set pressure (see \"closed outlets\" for more information). In a system with a condenser, vapor-locking may occur");
		}
		
		System.out.println("Entrance of highly volatile material");
		
		System.out.println("This is not in the scope of this project.");
		
		System.out.println("Overfilling");
		if(liquidFull)
		{
			System.out.println("See \"Closed Outlets\" for more information");
		}
		else if (vaporFull)
		{
			System.out.println("Vessel is normally vapor-filled");
		}
		else
		{
			System.out.println("Overfilling may result in overpressure. Refer to the liquid streams in \"Closed Outlets\" for more information");
		}
		
		System.out.println("Failure of automatic controls");
		System.out.println("Please refer to the scenarios attached to control valves in the \"Closed Outlet\" scenario");
		System.out.println("This tool does not distinguish between vessels/rotating equipment and control valves in this early release");
		
		System.out.println("Inadvertent Valve Opening");
		System.out.println("Please refer to the scenarios attached to manual valves in the \"Closed Outlet\" scenario");
		System.out.println("This tool does not distinguish between vessels/rotating equipment and manual valves in this early release");
		
		System.out.println("Abnormal heat or vapor input");
		
		if(hasHeatInput)
		{
			if(vaporFull)
			{
				System.out.println("Abnormal heat input may result in elevated downstream temperatures but this tool cannot determine whether or not overpressure may occur.");
			}
			else
			{
				System.out.println("Abnormal heat input may result in overpressure. The additional heat input may result in (additional) vapor generation.");
			}
		}
		else
		{
			System.out.println("No heat input is identified to be in this system.");
		}
		
		System.out.println("Split exchanger tube");
		{
			System.out.println("At this stage the tool does not differentiate between overpressure sources. If there is overpressure from a split exchanger tube, it will be listed in \"Closed Outlets\".");	
		}
		
		System.out.println("Internal Explosions");
		System.out.println("No source of internal explosion has been identified to be present in this system. (This is a default response)");
		
		System.out.println("Chemical Reaction");
		System.out.println("Chemcial reactions are outside the scope of this project.");
		
		System.out.println("Hydraulic Expansion");
		if(liquidFull)
		{
			System.out.println("Hydraulic expansion may result in overpressure if one condition is met: There is heat input or a liquid that is below 160°F that is exposed to solar radiation. In piping, solar heating relief requirements are expected to be small and relief valves are adequate by inspection. In heated exchangers and vessels a volume expansion calculation is necessary");
		}
		else
		{
			System.out.println("Hydraulic expansion is not expected to result in overpressure");
		}
		
		System.out.println("Exterior Fire");
		
		if(vaporFull)
		{
			System.out.println("for all equipment with surface area in the height of the fire (25 ft), a vapor expansion calc is necessary to identify the relief rate. Note that at 1100°F the vessel is expected to fail");
		}
		else
		{
			System.out.println("External fire may result in overpressure. Boiling liquid may have to be relievd through the relief valve.");
		}
		
		System.out.println("Power Failure Total");
		
		boolean powerfailureBlockedOutlet=false;
		boolean powerfailureReverseFlow=false;
		boolean powerfailurefeedloss=false;
		
		for(OPsources o:relevantEquipment)
		{
			if(o.powerDependent&&o.position==OPsources.OUTLET)
				powerfailureBlockedOutlet=true;
			if(o.powerDependent&&o.position==OPsources.OUTLET&&o.reverseFlow)
				powerfailureReverseFlow=true;
			if(o.powerDependent&&o.position==OPsources.FEED)
				powerfailurefeedloss=true;
		}
		if(powerfailureBlockedOutlet)
			System.out.println("Power failure is expected to result in a closed outlet");
		if(powerfailureReverseFlow)
			System.out.println("Power failure may result in reverse flow");
		if(powerfailurefeedloss)
			System.out.println("Power failure may result partial/full feed loss");
		
		System.out.println("instrument air failure");
		
		boolean IAcoolingfailure=false;
		boolean IAHeatInputIncrease=false;
		boolean IAfeedLoss=false;
		boolean IAblockedOutlet=false;
		
		for (OPsources o: relevantEquipment)
		{
			if(o.instrumentAirDependent&&o.failPosition==OPsources.FAIL_CLOSED&&o.position==OPsources.FEED)
				IAfeedLoss=true;
			if(o.instrumentAirDependent&&o.failPosition==OPsources.FAIL_CLOSED&&o.position==OPsources.OUTLET)
				IAblockedOutlet=true;
			if(o.instrumentAirDependent&&o.failPosition==OPsources.FAIL_OPEN&&o.position==OPsources.HEATINPUT)
				IAHeatInputIncrease=true;
			if(o.instrumentAirDependent&&o.failPosition==OPsources.FAIL_CLOSED&&o.position==OPsources.COOLING)
				IAcoolingfailure=true;
		}
		if(IAcoolingfailure)
			System.out.println("Instruemnt air failure may result in cooling failure");
		if(IAHeatInputIncrease)
			System.out.println("Instrument air failure may result in increased heat input");
		if(IAfeedLoss)
			System.out.println("Instrument air failure may result in a loss of feed");
		if(IAblockedOutlet)
			System.out.println("Instruemtn air failure may result in a closed outlet");
		
		
		return;
	}
	
}
