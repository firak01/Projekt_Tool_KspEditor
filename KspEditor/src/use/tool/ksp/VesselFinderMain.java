package use.tool.ksp;

import java.io.File;

import use.tool.ksp.object.VesselMatch;



public class VesselFinderMain {

    public static void main(String[] args) { 
          
    	try {
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\input\20260522experiment02.sfs" 
    		//"Float Raff 01experiment"
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem Spielstand .sfs>");
	            System.out.println("  java VesselEditorMainKSP <Name des gesuchten VESSEL innerhalb des Spielstands>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFilePath = args[0]; //D:\\KSP\\persistent_partstruktur.sfs
			  System.out.println(sFilePath);
			  File saveFile = new File(sFilePath);
			  

			  String vesselName = args[1]; //"Float Raff 01experiment";
			  System.out.println(vesselName);
			  
			  VesselMatch vessel =
					  VesselFinderTool.findSingleVesselByName(
                        saveFile,
                        vesselName);

			  System.out.println(
                "Vessel gefunden: "
                        + vessel.getVesselName());

			  System.out.println(
                "PART Anzahl: "
                        + vessel.countParts());

			  System.out.println(
                "Höchster PART-Index: "
                        + vessel.findHighestPartIndex());

			  vessel.debugWriteToFile(
                new File("exampleZZZ\\debug"));
    
    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


