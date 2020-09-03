package psvanalyzer;

//This class represents equipment being protected by PSV(s)

import java.util.Scanner;

public class Equipment
{
	String tag;//equipment nametag
	int type;//vessel,tower,Heat exchanger shell,heat exchanger tubes (low pressure tanks are not in scope)
	double MAWP;
	double MAWT;
	double elevation; //elevation to the bottom of the equipment in feet
	
	public static final int VESSEL=0;
	public static final int Tower=1;
	public static final int HEATEXCHANGERSHELL=2;
	public static final int HEATEXCHANGERTUBES=3;
	
	public Equipment(Scanner k)
	{
		tag="";
		type=0;
		MAWP=0;
		MAWT=0;
		elevation=0;
		
		this.setValues(k);
	}
	public void setValues(Scanner k)
	{
		return;//todo
	}
	public String toString()
	{
		return "Tag: "+tag+" type: "+type+" MAWP (psig): "+MAWP+" MAWT(°F) "+MAWT+" elevation(ft): "+elevation;
	}
}
