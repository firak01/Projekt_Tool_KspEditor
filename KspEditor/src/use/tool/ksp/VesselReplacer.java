package use.tool.ksp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.datatype.dateTime.DateTimeZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.FileTextReplacerZZZ;
import basic.zBasic.util.file.FileTextWriterZZZ;
import use.tool.ksp.object.FlightstateMatch;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsVesselParser;
import use.tool.ksp.util.VesselIdentity;
import use.tool.ksp.util.VesselValidator;

public class VesselReplacer extends AbstractVesselTool {

	public VesselReplacer() {
		super();
	}
    public VesselReplacer(SfsGameParser parser) {
        super(parser);
    }

    public void replaceVessel(File fileGame, File fileReplacementVessel) throws IOException, ExceptionZZZ {

        // 1. Vessel-Datei validieren
        VesselValidator.validateVesselFile(fileReplacementVessel);

        // 2. Replacement Vessel parsen
        SfsVesselParser objParserVessel = new SfsVesselParser(fileReplacementVessel);
		VesselMatch objReplacementVessel = objParserVessel.parse();
		
		String sPIdFromVessel = objReplacementVessel.getVesselPid();
		System.out.println("Vessel - Pid used:\t '" + sPIdFromVessel + "'");
		

		// 3. Spielstand parsen
        SfsGameParser objParserGame = new SfsGameParser(fileGame);
				
        // 4. Vessel im Spielstand finden
        VesselMatch objVesselToReplace = VesselFinder.findFirstVesselByPid(objParserGame, sPIdFromVessel);
        if(objVesselToReplace==null) {
        	ExceptionZZZ ez = new ExceptionZZZ("Vessel from VesselFile not found in Game. sPid='" + sPIdFromVessel + "'");
        	throw ez;
        }
        
        String sPIdFromGame = objVesselToReplace.getVesselPid();
		System.out.println("Game - Pid used:\t '" + sPIdFromGame + "'");
		
        // 5. Replacement Vessel extrahieren
		int iLineInFileStart = objVesselToReplace.getVesselStartLine_inFile();
		int iLineInFileEnd = objVesselToReplace.getVesselEndLine_inFile();
		List<String> listaStringReplacement = objVesselToReplace.getVesselLines();
				        
        // 6. Vessel ersetzen
		FileTextReplacerZZZ objFileTextReplacer = new FileTextReplacerZZZ(fileGame);
		boolean bSuccess = objFileTextReplacer.replace(iLineInFileStart, iLineInFileEnd, listaStringReplacement);
		if(bSuccess) {
			
			// 7. Geänderten Spielstand schreiben		      
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
    }

}
