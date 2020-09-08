package psvanalyzer;
import java.io.Serializable;
/*This class is used to represent sources of pressure for the system as well as other equipment that interacts with the system

*/
import java.util.Scanner;


public class OPsources implements Serializable
{
	String tag;//designation of equipment or utility 
	String Description;
	int position;//position relative to the feed (see static variables)
	double pressure;// in psig (if applicable)
	double flowRate;// in lb/hr (if applicable)
	boolean powerDependent;//Is this source coming from a pump or compressor?
	boolean instrumentAirDependent;//Does this use instrument air?
	int failPosition;//either FC,FO,FL
	boolean reverseFlow;//is this a piece of pressure generating equipment that could reverse flow into the system during a power failure
	
	public static final int FAIL_CLOSED=0;//these 3 are for the instrument air failure position
	public static final int FAIL_OPEN=1;
	public static final int FAIL_LAST=2;
	
	public static final int FEED=0;//this equipment feeds into the system
	public static final int OUTLET=1;//this equipment moves material from the system
	public static final int HEATINPUT=2;//this equipment provides heat to the system
	public static final int COOLING=3;//this provides system cooling.
	public static final int REFLUX=4;//this provides reflux to a distillation tower
	public static final int BOILUP=5;//this provides boilup to a distillation tower
	
	public OPsources (Scanner k)
	{
		tag="";//initialize all variables to prevent errors
		Description="";
		position=0;
		pressure=0;
		flowRate=0;
		powerDependent=false;
		instrumentAirDependent=false;
		failPosition=0;
		reverseFlow=false;
		
		setValues(k);
	}
	public void setValues(Scanner k)
	{
		System.out.println("What is the equipment tag?");
		tag=k.next();
		
		System.out.println("What is the name for this equipment?");
		Description=k.next();
		
		System.out.println("Please describe the relationship with the protected system(feed=0,outlet=1,heat input=2, cooling=3, reflux=4, boilup=5)");
		position=k.nextInt();
		
		System.out.println("What is the maximum pressure (psig) considered for this source. If this is a valve failure or opening scenario, you may consider the normal upstream pressure. If the equipment is downstream and reverse flow is not expected, enter a 0 for pressure.");
		pressure=k.nextDouble();
		
		System.out.println("What is the flow rate for this case? This can be based on ratings from rotating equipment, choked flow through control/bypass valves, or the known rate of flow");
		flowRate=k.nextDouble();
		
		System.out.println("Is this input/outlet dependent on electricity being available? (y/n)");
		String choice=k.next();
		if(choice.equals("y"))
			powerDependent=true;
		else if(choice.equals("n"))
			powerDependent=false;
		else
			System.out.println("Invalid input: defaulting to false");
		
		System.out.println("Is this input/outlet dependent on instrument air being available? (y/n)");
		choice=k.next();
		if(choice.equals("y"))
			instrumentAirDependent=true;
		else if(choice.equals("n"))
			instrumentAirDependent=false;
		else
			System.out.println("Invalid input: defaulting to false");
		
		System.out.println("If this equipment is dependent on instrument air, what is its fail position (FC=0,FO=1,FL=2)? If instrument air is no applicable enter 3");
		failPosition=k.nextInt();
		
		System.out.println("Is this is an outlet pump/compressor capable of reverse flow when power is lost? (y/n)");
		choice=k.next();
		if(choice.equals("y"))
			reverseFlow=true;
		else if(choice.equals("n"))
			reverseFlow=false;
		else
			System.out.println("Invalid input: defaulting to false");
		
	}
	public String toString()
	{
		return "tag: "+tag+"  "+Description+"  "+position+"  "+pressure+"  "+flowRate+"  "+powerDependent+"  "+instrumentAirDependent+" "+failPosition+"  "+reverseFlow;
	}
}