package use.tool.ksp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.dateTime.DateTimeZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.FileTextReplacerZZZ;
import basic.zBasic.util.file.FileTextWriterZZZ;
import use.tool.ksp.object.FlightstateMatch;
import use.tool.ksp.object.PartMatch;
import use.tool.ksp.object.VesselMatch;
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

    public boolean addStructure(File fileVessel, File fileStructure) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
	        SfsVesselParser objParserVessel = new SfsVesselParser(fileVessel);			
			bReturn = this.addStructure(objParserVessel, fileStructure);
    	}//end main:
    	return bReturn;
    }
    
    public boolean addStructure(File fileStructure) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
    			SfsVesselParser objParserVessel = (SfsVesselParser) this.getParser();//new SfsGameParser(fileGame);    			
    			bReturn = this.addStructure(objParserVessel, fileStructure);
    	}//end main:
    	return bReturn;
    }
    
    public boolean addStructure(SfsVesselParser objParserVessel, File fileStructure) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
//    		try {
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
		        SfsStructureParser objParserStructure = new SfsStructureParser(fileStructure);
				List<PartMatch> listaPart = objParserStructure.parse();
				
				System.out.println("Structure from file consists of the following PARTs with start and end line:");
				for(PartMatch objPart : listaPart) {
					System.out.println(objPart.getStartLine() + " - " + objPart.getEndLine() + "\t:" + objPart.getPartName());				
				}
				
				VesselMatch objVessel = objParserVessel.parse();				
				String sNameFromVessel = objVessel.getVesselName();
				System.out.println("Target Vessel - Name found:\t '" + sNameFromVessel + "'");
		
				List<String> listaStringReplacement = objVessel.getVesselLines();
				System.out.println("Target Vessel besitzt anfangs " + objVessel.countParts() + " PARTS.");
				
				
		        // 3. Vessel im Spielstand finden
//		        VesselFinder objVesselFinder = new VesselFinder(objParserGame);
//		        VesselMatch objVesselToReplace = objVesselFinder.findFirstVesselByPid(sPIdFromVessel);
//		                
//		        String sPIdFromGame = objVesselToReplace.getVesselPid();
//				System.out.println("Game - Pid used:\t '" + sPIdFromGame + "'");
//				
//		        // 4. Replacement Vessel extrahieren
//				int iLineInFileStart = objVesselToReplace.getVesselStartLine_inFile();
//				int iLineInFileEnd = objVesselToReplace.getVesselEndLine_inFile();
//								        
//		        // 5. Vessel ersetzen
//				File fileGame = objParserGame.getFile();
//				
//				FileTextReplacerZZZ objFileTextReplacer = new FileTextReplacerZZZ(fileGame);
//				boolean bSuccess = objFileTextReplacer.replace(iLineInFileStart, iLineInFileEnd, listaStringReplacement);
//				if(bSuccess) {
//					
//					// 6. Geänderten Spielstand schreiben		      
//					List<String> listLinesNew = objFileTextReplacer.getLines();
//					
//					String sFilePathTotal = fileGame.getAbsolutePath();
//					File fileDirectory = FileEasyZZZ.getDirectory(sFilePathTotal);
//							
//					String sFileGameOnly = FileEasyZZZ.getNameOnly(sFilePathTotal);
//					sFileGameOnly = sFileGameOnly + "_" + DateTimeZZZ.computeTimestampStringFormatedDefault();
//					String sFileGameNew = sFileGameOnly + ".sfs"; 
//		
//					
//					File fileDirectoryNew = new File("c:\\temp");
//					String sFilePathTotalNew = FileEasyZZZ.joinFilePathName(fileDirectoryNew, sFileGameNew);			
//					System.out.println("Writing new file: '" + sFilePathTotalNew + "'");
//					
//					FileTextWriterZZZ objFileTextWriter = new FileTextWriterZZZ(sFilePathTotalNew);
//					bSuccess = objFileTextWriter.writeLines(listLinesNew);
//				}
//    		}catch(IOException ioe) {
//    			ExceptionZZZ ez = new ExceptionZZZ(ioe);
//    			throw ez;
//    		}
    	}//end main:
    	return bReturn;
    }
}
