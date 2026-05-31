package use.tool.ksp;

import java.io.File;

import use.tool.ksp.object.VesselMatch;



public class VesselEditorMain {

    public static void main(String[] args) { 
          
    	try {
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\input\20260522experiment02.sfs" 
    		//"Float Raff 01experiment"
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\input\bohrer FGL_ohneAnbaustelle.sfs"
    		
    		//TODOGOON: Das Ziel, vorerst.
    		
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem Spielstand .sfs>");
	            System.out.println("  java VesselEditorMainKSP <Name des gesuchten VESSEL innerhalb des Spielstands>");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit der anzuhängenden Struktur .sfs>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFilePath = args[0]; 
			  System.out.println(sFilePath);
			  File saveFile = new File(sFilePath);
			  

			  String vesselName = args[1];
			  System.out.println(vesselName);
			  
			  String structureName = args[2]; 
			  System.out.println(structureName);
			  
			  //1. Schritt finde das Vessel, das zu bearbeiten ist im Save
			  VesselMatch vessel =
					  VesselFinder.findSingleVesselByName(
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

			  //2. Schritt Speichere das Vessel ab
			  vessel.debugWriteToFile(
                new File("exampleZZZ\\debug"));
    
    	
			  //3. Schritt Lade den Bohrer
			  //...usw.
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


