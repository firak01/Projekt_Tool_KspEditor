package use.tool.ksp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.dateTime.DateTimeZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.txt.stream.FileTextReplacerZZZ;
import basic.zBasic.util.file.txt.stream.FileTextWriterZZZ;
import use.tool.ksp.object.FlightstateMatch;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsVesselParser;
import use.tool.ksp.util.VesselIdentity;
import use.tool.ksp.util.VesselValidator;

public class VesselReplacer extends AbstractParserUsingTool {

	public VesselReplacer() {
		super();
	}
    public VesselReplacer(SfsGameParser parser) {
        super(parser);
    }

    public boolean replaceVessel(File fileGame, File fileReplacementVessel) throws IOException, ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
	        SfsGameParser objParserGame = new SfsGameParser(fileGame);			
			bReturn = this.replaceVessel(objParserGame, fileReplacementVessel);
    	}//end main:
    	return bReturn;
    }
    
    public boolean replaceVessel(File fileReplacementVessel) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
    			SfsGameParser objParserGame = (SfsGameParser) this.getParser();//new SfsGameParser(fileGame);    			
    			bReturn = this.replaceVessel(objParserGame, fileReplacementVessel);
    	}//end main:
    	return bReturn;
    }
    
    public boolean replaceVessel(SfsGameParser objParserGame, File fileReplacementVessel) throws ExceptionZZZ {
    	boolean bReturn = false;
    	main:{
//    		try {
    			if(objParserGame==null) {
    				ExceptionZZZ ez = new ExceptionZZZ("GameParser - Object", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
    				throw ez;
    			}
    			
    			
    			if(fileReplacementVessel==null) {
    				ExceptionZZZ ez = new ExceptionZZZ("FileVessel - Object", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
    				throw ez;
    			}
    			
		        // 1. Vessel-Datei validieren
		        VesselValidator.validateVesselFile(fileReplacementVessel);
		
		        // 2. Replacement Vessel parsen
		        SfsVesselParser objParserVessel = new SfsVesselParser(fileReplacementVessel);
				VesselMatch objReplacementVessel = objParserVessel.parse();
				
				String sPIdFromVessel = objReplacementVessel.getVesselPid();
				System.out.println("Vessel - Pid used:\t '" + sPIdFromVessel + "'");
				
				String sNameFromVessel = objReplacementVessel.getVesselName();
				System.out.println("Vessel - Name found:\t '" + sNameFromVessel + "'");
		
				List<String> listaStringReplacement = objReplacementVessel.getVesselLines();
									
		        // 3. Vessel im Spielstand finden
		        VesselFinder objVesselFinder = new VesselFinder(objParserGame);
		        VesselMatch objVesselToReplace = objVesselFinder.findFirstVesselByPid(sPIdFromVessel);
		                
		        String sPIdFromGame = objVesselToReplace.getVesselPid();
				System.out.println("Game - Pid used:\t '" + sPIdFromGame + "'");
				
		        // 4. Replacement Vessel extrahieren
				int iLineInFileStart = objVesselToReplace.getVesselStartLine_inFile();
				int iLineInFileEnd = objVesselToReplace.getVesselEndLine_inFile();
								        
		        // 5. Vessel ersetzen
				File fileGame = objParserGame.getFile();
				
				FileTextReplacerZZZ objFileTextReplacer = new FileTextReplacerZZZ(fileGame);
				boolean bSuccess = objFileTextReplacer.replace(iLineInFileStart, iLineInFileEnd, listaStringReplacement);
				if(bSuccess) {
					
					// 6. Geänderten Spielstand schreiben		      
					List<String> listLinesNew = objFileTextReplacer.getLines();
					
					String sFilePathTotal = fileGame.getAbsolutePath();
					File fileDirectory = FileEasyZZZ.getDirectory(sFilePathTotal);
							
					String sFileGameOnly = FileEasyZZZ.getNameOnly(sFilePathTotal);
					sFileGameOnly = sFileGameOnly + "_" + DateTimeZZZ.computeTimestampStringFormatedDefault();
					String sFileGameNew = sFileGameOnly + ".sfs"; 
		
					
					File fileDirectoryNew = new File("c:\\temp");
					String sFilePathTotalNew = FileEasyZZZ.joinFilePathName(fileDirectoryNew, sFileGameNew);			
					System.out.println("Writing new file: '" + sFilePathTotalNew + "'");
					
					FileTextWriterZZZ objFileTextWriter = new FileTextWriterZZZ(sFilePathTotalNew);
					bSuccess = objFileTextWriter.writeLines(listLinesNew);
				}
//    		}catch(IOException ioe) {
//    			ExceptionZZZ ez = new ExceptionZZZ(ioe);
//    			throw ez;
//    		}
    	}//end main:
    	return bReturn;
    }
}
