package use.tool.ksp;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

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

		  List<String> listaLine = Files.readAllLines(
	                objFileIn.toPath(),
	                Charset.forName("UTF-8")
	        );
		  
          // Beispiel:
          // Erster PART bekommt parent = 999
          // Zweiter PART bekommt parent = 2000
          // Dritter PART bekommt parent = 2001
          // Vierter PART bekommt parent = 2002
		  List<String> listaLineOut = StructureEditor.updateParentValues(
				  listaLine,
                  iIndexAufhaenger,
                  iIndexStrukturStart                  
          );
		  
		  //String sAttnNodeDefault = "bottom";
		  //List<String> listaLineOut02 = StructureEditor.updateAttnValues(listaLineOut, iIndexAufhaenger, iIndexStrukturStart, sAttnNodeDefault);

		  //Ausgabedatei
		  String sSuffix = "_STEP01";
		  File objFileOut = StructureEditor.createOutputFile(objFileIn, sSuffix);
		  StructureEditor.writeLines(objFileOut, listaLineOut);

	      System.out.println("Datei gespeichert:");
	      System.out.println(objFileOut.getAbsolutePath());
		  
          System.out.println("Fertig.");

      } catch (Exception e) {
          e.printStackTrace();
      }
	}
}
