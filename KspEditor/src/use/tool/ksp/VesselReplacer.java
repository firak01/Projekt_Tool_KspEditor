package use.tool.ksp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import basic.zBasic.ExceptionZZZ;
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
        VesselValidator.validateVessel(fileReplacementVessel);

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
        
        // 6. Vessel ersetzen
        
        // 7. Spielstand schreiben
       
    }

}
