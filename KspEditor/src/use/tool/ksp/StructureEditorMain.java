package use.tool.ksp;

import java.io.File;

public class StructureEditorMain {
	public static void main(String[] args) {
		try {
			//AUSGANGSSTRUKTUR
			//"C:\1fgl\repo\EclipseOxygen_V02\Projekt_Tool_KspEditor\KspEditor\exampleZZZ\StructureEditorMain\input\bohrer FGL_ohneAnbaustelle.sfs"
			//
			//"dynamsicher" Index des Aufhängerelments
			//64
			//
			//"dynamischer" Index des ersten PART der Struktur im neuen VESSEL (Entspricht der "Anzahl der PARTS im bisherigen VESSEL minus 1")
			//255
			//
			if (args.length < 1) {
	            System.out.println("Verwendung:");
	            System.out.println("  java StructureEditorMainKSP <pfad-zur-Datei der Struktur .sfs>");
	            System.out.println("  java StructureEditorMainKSP <Index des Aufhänger PART des VESSEL fuer die Struktur>");
	            System.out.println("  java StructureEditorMainKSP <Index des ersten PART der Struktur (Merke: Struktur wird immer ans Ende gehaengt, also Anzahl der bisherigen Teile minus 1>");
	            return;
	        }else {
	        	System.out.println("Start, verwende Argumente:");        	
	        }
			
		  String sFilePath = args[0]; //D:\\KSP\\persistent_partstruktur.sfs
		  File objFileIn = new File(sFilePath);
		  
		  String sIndexAufhaenger=args[1];
		  int iIndexAufhaenger = Integer.valueOf(sIndexAufhaenger);
		  
		  String sIndexStrukturStart = args[2];
		  int iIndexStrukturStart = Integer.valueOf(sIndexStrukturStart);

          // Beispiel:
          // Erster PART bekommt parent = 999
          // Zweiter PART bekommt parent = 2000
          // Dritter PART bekommt parent = 2001
          // Vierter PART bekommt parent = 2002
		  StructureEditor.updateParentValues(
                  objFileIn,
                  iIndexAufhaenger,
                  iIndexStrukturStart,
                  "_STEP01"
          );

          System.out.println("Fertig.");

      } catch (Exception e) {
          e.printStackTrace();
      }
	}
}
