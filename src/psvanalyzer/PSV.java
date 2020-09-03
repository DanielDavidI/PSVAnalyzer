package psvanalyzer;

import java.util.Scanner;

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
	
	public PSV()//only used to generate dummy object to get path
	{
		return;
	}
	
	public PSV(Scanner k)
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
		this.setValues(k);
	}
	public void setValues(Scanner k)
	{
		return;
	}
	public String toString()
	{
		return "Tag: "+name+" Model number: "+model+" Manufacturer: "+manufacturer+" Type: "+type+" relief location: "+reliefLocation+" orifice letter: "+OrificeLetter+" orifice area (in²) "+OrificeArea+" Kd: "+Kd+" set pressure (psig): "+setPressure+" CDTP (psig): "+CDTP;
	}

}
