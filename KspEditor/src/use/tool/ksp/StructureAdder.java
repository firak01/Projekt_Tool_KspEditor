package use.tool.ksp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.abstractList.ListUtilZZZ;
import basic.zBasic.util.datatype.dateTime.DateTimeZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.FileTextInserterZZZ;
import basic.zBasic.util.file.FileTextReplacerZZZ;
import basic.zBasic.util.file.FileTextWriterZZZ;
import use.tool.ksp.object.FlightstateMatch;
import use.tool.ksp.object.PartMatch;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.ISfsStructureParser;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsStructureParser;
import use.tool.ksp.util.SfsVesselParser;
import use.tool.ksp.util.StructureValidator;
import use.tool.ksp.util.VesselIdentity;
import use.tool.ksp.util.VesselValidator;

public class StructureAdder extends AbstractParserUsingTool {

	public StructureAdder() {
		super();
	}
    public StructureAdder(SfsVesselParser parser) {
        super(parser);
    }

    public boolean addStructure(File fileVessel, File fileStructure, int iPegElementIndex, ISfsStructureParser.PegPartNode enumPegPartNode) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
	        SfsVesselParser objParserVessel = new SfsVesselParser(fileVessel);			
			bReturn = this.addStructure(objParserVessel, fileStructure, iPegElementIndex, enumPegPartNode);
    	}//end main:
    	return bReturn;
    }
    
    public boolean addStructure(File fileStructure, int iPegElementIndex, ISfsStructureParser.PegPartNode enumPegPartNode) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
    			SfsVesselParser objParserVessel = (SfsVesselParser) this.getParser();//new SfsGameParser(fileGame);    			
    			bReturn = this.addStructure(objParserVessel, fileStructure, iPegElementIndex, enumPegPartNode);
    	}//end main:
    	return bReturn;
    }
    
    public boolean addStructure(SfsVesselParser objParserVessel, File fileStructure, int iPegElementIndex, ISfsStructureParser.PegPartNode enumPegPartNode) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
			if(objParserVessel==null) {
				ExceptionZZZ ez = new ExceptionZZZ("VesselParser - Object", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
				throw ez;
			}
			
			
			if(fileStructure==null) {
				ExceptionZZZ ez = new ExceptionZZZ("FileStructure - Object", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
				throw ez;
			}
			
	        // 1. Structure-Datei validieren
	        StructureValidator.validateStructureFile(fileStructure);
	
	        // 2. Hinzuzufügende Struktur parsen
	        // Vorausgesetzt, das neben der formalen, validierten Struktur auch richtig ist
	        // a) die ParentId des ersten StrukturPART muss der "dynamischen" ID des Aufhängerelements entsprechen
	        // b) die ParentID des jeweils nachfolgenden StrukturPART muss der "dynamischen" ID des vorherigen StrukturPART entsprechen
	        // b) der attN - Wert analog
	        // Dies erreicht man durch den Struktur Editor
	        SfsStructureParser objParserStructure = new SfsStructureParser(fileStructure);
			List<PartMatch> listaPartAttachment = objParserStructure.parse();
			List<String> listasPartAttachment = objParserStructure.toRawString(listaPartAttachment);
			
			System.out.println("Structure from file consists of the following PARTs with start and end line:");
			for(PartMatch objPart : listaPartAttachment) {
				System.out.println(objPart.getStartLine() + " - " + objPart.getEndLine() + "\t:" + objPart.getPartName());				
			}
			
			VesselMatch objVessel = objParserVessel.parse();				
			String sNameFromVessel = objVessel.getVesselName();
			System.out.println("Target Vessel - Name found:\t '" + sNameFromVessel + "'");					
			System.out.println("Target Vessel besitzt anfangs " + objVessel.countParts() + " PARTS.");
			
			int iLineStart = objVessel.getVesselPartStartLine();
			int iLineEnd = objVessel.getVesselPartEndLine();
			System.out.println("Target Vessel PART-Abschnitte von " + iLineStart+ " - " + iLineEnd);
			
			int iLineStart_inFile = objVessel.getVesselPartStartLine_inFile();
			int iLineEnd_inFile = objVessel.getVesselPartEndLine_inFile();
			System.out.println("Target Vessel PART-Abschnitte in Datei von " + iLineStart_inFile+ " - " + iLineEnd_inFile);
			
			int iLineStart_inFlightstate = objVessel.getVesselPartStartLine_inFlightstate();
			int iLineEnd_inFlightstate = objVessel.getVesselPartEndLine_inFlightstate();
			System.out.println("Target Vessel PART-Abschnitte in Flightstate von " + iLineStart_inFlightstate+ " - " + iLineEnd_inFlightstate);
			
			//3.  Bearbeite die bereits vorhandenen PART des Vessel
			//3a. Ermittle das "Aufhängerelement" also das PART an der angegeben Stelle
						
			//Es muss die "dynamische ID" des so zu korregierenden PART im INPUT-VESSEL übergeben werden
			//Diese bekommt man durch Analyse des INPUT-VESSEL mit dem Tool "KLM". (suche nach dem Part Namen. Dann prüfen des vorherigen Part Namen.)
			//Das ist vom Typ her das gesuchte Anbindeelement
			//name = structuralMiniNode
			//
			//Darin ist tatsächlich eine Zeile enthalten
			//attN = bottom, -1
			//Dies ist also unverbunden, im Gegensatz zur Zeile
			//attN = front, 63
			//Dann gibt es noch ungenutzte Verbindungstellen
			//attN = right, -1
			//attN = left, -1
			//attN = back, -1
			//attN = top, -1
			
			File fileVessel = objParserVessel.getFile();
			SfsStructureParser objParserVesselStructure = new SfsStructureParser(fileVessel);
			List<PartMatch> listaVesselPart = objParserVesselStructure.parse();
			
			PartMatch objPartPeg = listaVesselPart.get(iPegElementIndex);
			String sPegElementNode = enumPegPartNode.name();;
			System.out.println("Die Struktur soll an diesen Node des Aufhängerelements gehängt werden: '" + sPegElementNode + "'");
			
			//Es fehlt das Anpassen der attN - Werts mit der "dynamischen ID", 
			//welche für die angehängte Struktur vergeben wird.
			//Diese ist Anzahl der Teile. (darin ist +1 schon wg, indexanfang bei 0)
			//Also: objVessel.countParts()
			
			//3b) Ersetze den attN-String für den gewünschten "Node" an die passende Stelle des letzten PART
			int iIndexPartNew = objVessel.countParts();
			String sAttN = "attN = " + sPegElementNode;
			String sAttNValueNew = sAttN + ", " + iIndexPartNew;
			System.out.println(sAttNValueNew);
			
			List<String> listaPartPegLine = objPartPeg.getLines();
			int i = 0;
			for(String sLine : listaPartPegLine) {
				if(StringZZZ.startsWith(sLine.trim(), sAttN)) {
					listaPartPegLine.set(i, sAttNValueNew);
					System.out.println("Neuen Wert gesetzt in das Aufhänger-PART-Element");
					break;
				}
				i++;
			}
			
		
			//3c) Geänderten PART - Abschnitts wieder in das VESSEL einfügen
			List<String> listasVessel = objVessel.getVesselLines();
			
			//Merke: Da in den Parsern keine Liste verwaltet wird, kann dort die Änderung so auch nicht ankommen...
			
			//DEBUG: Alle attN, bottom Zeilen..... sind sie im Vesel angekommen?
			//Im Vessel erst einmal nicht....
//			for(String sLine : listaStringVessel) {
//				if(StringZZZ.startsWith(sLine.trim(), sAttN)) {					
//					System.out.println(sLine);
//				}
//				i++;
//			}
			
			//DEBUG: 
			//In listaVesselPart..... die die geänderten Zeile irgendwo angekommen?
			//JA, ist da.....
//			for(PartMatch objPartMatch : listaVesselPart) {
//				List<String> listasLine = objPartMatch.getLines();
//				for(String sLine : listasLine) {
//					if(StringZZZ.startsWith(sLine.trim(), sAttN)) {					
//						System.out.println(sLine);
//					}
//				}
//			}
			
			//Mache aus der PART-Liste eine neue ArrayList. listaVesselPart hat ja die geänderte Zeile mitbekommen.
			List<String> listasVesselPart = objParserVesselStructure.toRawString(listaVesselPart);
			
			//Ersetze die Liste im Vessel durch die neue PART-Liste
			List<String> listasVesselNew = ListUtilZZZ.replace(listasVessel, iLineStart, iLineEnd, listasVesselPart); 
				
			//ABER DAMIT IST BISHER NUR DAS AUFHÄNGERELEMENT KORREGIERT, NOCH NICHT DIE NEUE STRUKTUR AUFGENOMMEN
			//4. Unterhalb des letzten PART - Abschnitts die neue Struktur anhängen
			FileTextInserterZZZ objFileTextInserter = new FileTextInserterZZZ(listasVesselNew);
			boolean bSuccess = objFileTextInserter.insertBehind(iLineEnd_inFile, listasPartAttachment);
			if(bSuccess) {
				
				//4. Geändertes Vessel-File schreiben
				List<String> listLinesNew = objFileTextInserter.getLines();
								
				String sFilePathTotal = fileVessel.getAbsolutePath();
				File fileDirectory = FileEasyZZZ.getDirectory(sFilePathTotal);
						
				String sFileVesselOnly = FileEasyZZZ.getNameOnly(sFilePathTotal);
				sFileVesselOnly = sFileVesselOnly + "_" + DateTimeZZZ.computeTimestampStringFormatedDefault();
				String sFileGameNew = sFileVesselOnly + ".sfs"; 
	
				
				File fileDirectoryNew = new File("c:\\temp");
				String sFilePathTotalNew = FileEasyZZZ.joinFilePathName(fileDirectoryNew, sFileGameNew);			
				System.out.println("Writing new file: '" + sFilePathTotalNew + "'");
				
				FileTextWriterZZZ objFileTextWriter = new FileTextWriterZZZ(sFilePathTotalNew);
				bReturn = objFileTextWriter.writeLines(listLinesNew);
			}
    	}//end main:
    	return bReturn;
    }
}
