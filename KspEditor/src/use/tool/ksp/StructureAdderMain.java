package use.tool.ksp;

import java.io.File;

import basic.zBasic.util.datatype.enums.EnumHelperZZZ;
import basic.zBasic.util.datatype.enums.EnumUtilZZZ;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.ISfsStructureParser;
import use.tool.ksp.util.SfsGameParser;



/**Ergänze ein VESSEL (Vessel-Datei) um die Struktur mehrerer PARTS (Strucure-Datei)
 * @author Fritz Lindhauer
 *
 */
public class StructureAdderMain {

    public static void main(String[] args) { 
          
    	try {
    		
    		//VESSEL
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\StructureAdderMain\input\VESSEL_Float_Raff_01experiment_ohneDieStruktur.sfs"
    		
    		//STRUCTURE
    		//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\StructureAdderMain\input\bohrer FGL_ohneAnbaustelle.sfs"
    		
    		//iPegElementIndex (aus dem VESSEL per Tool "KLM" herausgesucht, hier muss der attN Wert gesetzt werden als Verbindung zur neuen Struktur)
    		//64
    		//
    		//attN Verbindungsstelle, hier Beispiele für ein Aufhänger element mit: name = structuralMiniNode
			//attN = bottom, -1
			//attN = front, 63
			//attN = right, -1
			//attN = left, -1
			//attN = back, -1
			//attN = top, -1
    		//wir wollen am "bottom" anbinden.
    		//bottom
    		
    		
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit dem VESSEL .sfs>");
	            System.out.println("  java VesselEditorMainKSP <pfad-zur-Datei mit der Struktur .sfs>");
	            System.out.println("  java VesselEditorMainKSP <Indexzahl des Aufhängerelements als Verbindungstelle zur neuen Struktur. Hat z.B. name = structuralMiniNode>");
	            System.out.println("  java VesselEditorMainKSP <Name des Verbindungstellen'ports'. Gültig sind für Aufhängerelement mit name = structuralMiniNode: bottom, front, left, right, back, top>");
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
			  
			  String sPegElementIndex = args[2];
			  System.out.println(sPegElementIndex);
			  int iPegElementIndex = new Integer(sPegElementIndex).intValue();
			  
			  String sPegElementNode = args[3];
			  System.out.println(sPegElementNode);
			  
			  //Wie nun auf das Enum kommen???
			  //https://stackoverflow.com/questions/8108980/java-using-enum-with-switch-statement
			  int iPegElementNode = 1;
			  ISfsStructureParser.PegPartNode whichEnumPegPartNode = ISfsStructureParser.PegPartNode.values()[iPegElementNode];
			  
			  
			  switch(whichEnumPegPartNode) {
			  case back:
				  
				break;
			  
			  default:{
				System.out.println("Dieser Typ wird nicht behandelt: '" + whichEnumPegPartNode.name() + "'" );
				break;
			  }
			  
			  TODOGOON20260606;
			  //Meine IDEE: Gehe in einer Schleife von 1 bis 6 alle enums durch 
			  //            Vergleiche dann den Namen mit sPegElementNode
			  //            Bei Gleichstand hat man das enum gefunden...

			  //Merke: EnumHelperZZZ sucht enum nach dem Namen in einer Klasse.
			  //Am ehesten wäre noch EnumUtilZZZ zu erweitern
			  //um:
			  //
			  ISfsStructureParser.PegPartNode whichEnumPegPartNode2 = EnumUtilZZZ.getEnumByName(ISfsStructureParser.PegPartNode.values(), sPegElementNode);
			  
    	}	
			  
			  
			  StructureAdder objStructureAdder = new StructureAdder();
			  boolean bSuccess = objStructureAdder.addStructure(fileVessel, fileStructure, iPegElementIndex, enumPegPartNode);
			 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}


