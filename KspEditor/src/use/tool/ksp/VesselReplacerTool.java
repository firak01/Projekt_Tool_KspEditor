package use.tool.ksp;

import java.io.IOException;
import java.nio.file.Path;

import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.SfsParser;
import use.tool.ksp.util.VesselIdentity;
import use.tool.ksp.util.VesselValidator;

public class VesselReplacerTool extends AbstractVesselTool {

    public VesselReplacerTool(SfsParser parser) {
        super(parser);
    }

    public void replaceVessel(
            Path gameFile,
            Path replacementVesselFile,
            Path outputFile,
            VesselIdentity identity)
            throws IOException {

        // 1. Vessel-Datei validieren
        VesselValidator.validateVesselFile(replacementVesselFile);

        // 2. Spielstand parsen
       
        // 3. Replacement Vessel parsen
       
        // 4. Vessel im Spielstand finden
       

        // 5. Replacement Vessel extrahieren
        
        // 6. Vessel ersetzen
        
        // 7. Spielstand schreiben
       
    }

}
