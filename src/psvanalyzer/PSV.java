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
		System.out.println("What is the tag for this PSV?");
		name=k.next();
		
		System.out.println("What is the model for this PSV?");
		model=k.next();
		
		System.out.println("What is the manufacturer for this PSV?");
		manufacturer=k.next();
		
		System.out.println("What is the tpye (conventional/bellows/pilot) for this PSV?");
		type=k.next();
		
		System.out.println("What is the relief desination (atmosphere/flare/sewer) for this PSV?");
		reliefLocation=k.next();
		
		System.out.println("What is the API orifice letter (use X if a standard API orifice is not used) for this PSV?");
		OrificeLetter=k.next();
		
		System.out.println("What is the orifice area (in²) for this PSV?");
		OrificeArea=k.next();
		
		System.out.println("What is the Kd for this PSV?");
		Kd=k.nextDouble();
		
		System.out.println("What is the set pressure (psig) for this PSV?");
		setPressure=k.nextDouble();
		
		System.out.println("What is the CDTP (psig) for this PSV?");
		CDTP=k.nextDouble();
		
		return;
	}
	public String toString()
	{
		return "Tag: "+name+" Model number: "+model+" Manufacturer: "+manufacturer+" Type: "+type+" relief location: "+reliefLocation+" orifice letter: "+OrificeLetter+" orifice area (in²) "+OrificeArea+" Kd: "+Kd+" set pressure (psig): "+setPressure+" CDTP (psig): "+CDTP;
	}

}
