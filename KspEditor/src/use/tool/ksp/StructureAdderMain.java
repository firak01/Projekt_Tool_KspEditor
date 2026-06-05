package use.tool.ksp;

import java.io.File;

import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;



public class StructureAdderMain {

    public static void main(String[] args) { 
          
    	try {
    		
    		//VESSEL
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\StructureAdderMain\input\VESSEL_Float_Raff_01experiment.sfs"
    		
    		//STRUCTURE
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\StructureAdderMain\input\bohrer FGL_ohneAnbaustelle.sfs"
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem VESSEL .sfs>");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit der Struktur .sfs>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFileVessel = args[0]; 
			  System.out.println(sFileVessel);
			  File fileVessel = new File(sFileVessel);
			  if(!fileVessel.exists()) throw new IllegalArgumentException("No vessel file found at '" + sFileVessel + "'" );

			  String sFilePathStructure = args[1];
			  System.out.println(sFilePathStructure);
			  File fileStructure = new File(sFilePathStructure);
			  if(!fileStructure.exists()) throw new IllegalArgumentException("No structure file found at '" + sFilePathStructure + "'" );
			  
			  StructureAdder objStructureAdder = new StructureAdder();
			  boolean bSuccess = objStructureAdder.addStructure(fileVessel, fileStructure);
			 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


