package com.toSWF;

import java.util.ArrayList;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class TestPrint2Flash
{

	public static void main(String[] args) throws java.io.IOException
	{
		ReleaseManager rm = new ReleaseManager();
		ArrayList<String> pathes=new ArrayList<String>();
		pathes.add("D:\\易城研发部项目汇总说明.txt");
		try
		{
			// Create Server object
			IDispatch p2f = new IDispatch(rm, "P2F.Server"); 

			// Setup interface and protection options
			IDispatch defProfile = (IDispatch)p2f.get("DefaultProfile");
			defProfile.put("InterfaceOptions", P2FConst.INTLOGO | P2FConst.INTZOOMSLIDER | P2FConst.INTPREVPAGE | P2FConst.INTGOTOPAGE | P2FConst.INTNEXTPAGE);
			defProfile.put("ProtectionOptions", P2FConst.PROTDISPRINT);

			// Convert document
			
			p2f.method("ConvertFile", pathes.toArray());
			System.out.println("Conversion completed successfully");
		}
		catch (Exception e)
		{
			System.out.println("An error occurred at conversion:");
			e.printStackTrace();
		}
		finally
		{
			rm.release();
		}
	}
}
