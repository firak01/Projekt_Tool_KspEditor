package use.tool.ksp;

import java.io.File;

import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsVesselParser;



public class VesselEditorMain {

    public static void main(String[] args) { 
          
    	try {
    		
    		//GEHE HIER VON DER VESSEL DATEI AUS, SPIELSTANDDATEI WIRD IM GAME EDITOR VERARBEITET   
    		
    		//Das Ziel: Hänge an ein Vessel eine Struktur an. Die Struktur ist schon so vorbereitet, das sie von der PARENTID, etc. passt.
    		if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMain <pfad-zur-Datei mit dem VESSEL .sfs>");	            
	            System.out.println("  java VesselEditorMain <pfad-zur-Datei mit der anzuhängenden Struktur .sfs>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
			  String sFilePathVessel = args[0]; 
			  System.out.println(sFilePathVessel);
			  File fileVessel = new File(sFilePathVessel);
			  
			  String sFilePathStructure = args[1]; 
			  System.out.println(sFilePathStructure);
			  File fileStructure = new File(sFilePathStructure);
			  
    		
			  SfsVesselParser objParserVessel = new SfsVesselParser(fileVessel);				
			  VesselEditor objVesselEditor = new VesselEditor(objParserVessel);
			 
			  //Hänge die Struktur an:
			  boolean bSuccess = objVesselEditor.addStructure(fileStructure);
			  if(bSuccess) {
				  System.out.println("Anhängen der Struktur erfolgreich");
			  }
			  
			  
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


