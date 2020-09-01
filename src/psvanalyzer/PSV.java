package psvanalyzer;

public class PSV 
{
	String name;
	String model;
	String manufacturer;
	String type;
	String reliefLocation;
	String OrificeLetter;//API orifice designation. Use X for ASME or noncode valve
	String OrificeArea;
	double Kd;//make sure that both API and Kd values correspond to either API or ASME
	double setPressure;//psig
	double CDTP;//psig
	
	
	
	public PSV()
	{
		name="";
		model="";
		manufacturer="";
		type="";
		reliefLocation="";
		OrificeLetter="";
		OrificeArea="";
		Kd=0;
		setPressure=0;
		CDTP=0;
		return;//do not use this constructor normally. This is used for the object needed to get the file path
	}

}
