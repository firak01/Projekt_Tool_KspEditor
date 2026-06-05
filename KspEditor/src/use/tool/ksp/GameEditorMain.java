package use.tool.ksp;

import java.io.File;

import basic.zBasic.util.datatype.string.StringZZZ;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsVesselParser;

public class GameEditorMain {

	public static void main(String[] args) {
      
    	try {    		
    		//SPIELSTAND
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\GameEditorMain\input\20260522experiment02.sfs"
    		
    		//VESSELNAME im Spielstand
    		//"Float Raff 01experiment"
    		    		 
    		//DATEI MIT ERSETZENDEM VESSEL
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\GameEditorMain\input\VESSEL_Float_Raff_01experiment.sfs"
    		
			//TODOGOON: Steuere über Argumente was zu tun ist, jetzt erst einmal:
			//Tausche das Vessel aus:
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java GameEditorMainKSP <pfad-zur-Datei mit dem Spielstand .sfs>");
	            System.out.println("  java GameEditorMainKSP <pfad-zur-Datei mit dem VESSEL .sfs>");
	            System.out.println("  java GameEditorMainKSP <pfad-zur-Datei mit der Struktur .sfs>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFilePathGame = args[0]; 
			  System.out.println(sFilePathGame);
			  File fileGame = new File(sFilePathGame);
			  if(!fileGame.exists()) throw new IllegalArgumentException("No game file found at '" + sFilePathGame + "'" );

			  String sNameVessel = args[1];
			  System.out.println(sNameVessel);			 
			  if(StringZZZ.isEmpty(sNameVessel)) throw new IllegalArgumentException("No vessel name provided");
			  
			  String sFilePathVessel = args[2];
			  System.out.println(sFilePathVessel);
			  File fileVessel = new File(sFilePathVessel);
			  if(!fileVessel.exists()) throw new IllegalArgumentException("No vessel file found at '" + sFilePathVessel + "'" );
			  
			  SfsGameParser objParserGame = new SfsGameParser(fileGame);				
			  GameEditor objGameEditor = new GameEditor(objParserGame);
			 
			  //Tausche das Vessel aus:
			  boolean bSuccess = objGameEditor.replaceVessel(fileVessel);
			  if(bSuccess) {
				  System.out.println("Austausch des Vessel erfolgreich");
			  }
			  
			  
    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}
