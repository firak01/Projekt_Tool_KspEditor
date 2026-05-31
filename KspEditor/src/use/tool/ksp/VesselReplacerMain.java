package use.tool.ksp;

import java.io.File;

import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;



public class VesselReplacerMain {

    public static void main(String[] args) { 
          
    	try {
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\input\20260522experiment02.sfs" 
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\debug\VESSEL_Float_Raff_01experiment.sfs"
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem Spielstand .sfs>");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem VESSEL .sfs>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFilePathGame = args[0]; 
			  System.out.println(sFilePathGame);
			  File fileGame = new File(sFilePathGame);
			  if(!fileGame.exists()) throw new IllegalArgumentException("No game file found at '" + sFilePathGame + "'" );

			  String sFilePathVessel = args[1];
			  System.out.println(sFilePathVessel);
			  File fileVessel = new File(sFilePathGame);
			  if(!fileVessel.exists()) throw new IllegalArgumentException("No vessel file found at '" + sFilePathVessel + "'" );
			  
			  VesselReplacer objReplacerVessel = new VesselReplacer();
			  objReplacerVessel.replaceVessel(fileGame, fileVessel);
			  
			  
			  
    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


