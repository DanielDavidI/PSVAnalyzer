package psvanalyzer;
/*This class is used to represent sources of pressure for the system as well as other equipment that interacts with the system

*/
import java.util.Scanner;


public class OPsources 
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
	public static final int HEATINPUTt=2;//this equipment provides heat to the system
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
		
	}
	public String toString()
	{
		return "tag: "+tag+"  "+Description+"  "+position+"  "+pressure+"  "+flowRate+"  "+powerDependent+"  "+instrumentAirDependent+""+failPosition+"  "+reverseFlow;
	}
}





/*public class Equipment 
{
	String tag;//what is the designated 
	String Category;//is this a pump, compressor, blower, control valve, bypass valve?
	boolean usesPower;//will this be effected by power failure?
	boolean instrumentAirUser;//Is this equipment effected by the instrument air header?
	String failPosition;//Only applicable if instrumentAirUser is true.
	String Position;//What position does the equipment occupy compared to the system. (see static variables)
	
	
	
	public Equipment()
	{
		tag="";
		Category="";
		usesPower=false;
		instrumentAirUser=false;
		failPosition="";
		Position="";
	}
	
}*/