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
        SfsNode gameRoot = parser.parse(gameFile);

        // 3. Replacement Vessel parsen
        SfsNode replacementRoot = parser.parse(replacementVesselFile);

        // 4. Vessel im Spielstand finden
        VesselMatch match =
                VesselFinderTool.findByPersistenceId(gameRoot,
                                                identity.getPersistenceId());

        if (match == null) {
            throw new IllegalStateException(
                    "Target vessel not found: "
                    + identity.getPersistenceId());
        }

        // 5. Replacement Vessel extrahieren
        SfsNode replacementVessel =
                extractSingleVesselNode(replacementRoot);

        // 6. Vessel ersetzen
        replaceNode(match.getNode(), replacementVessel);

        // 7. Spielstand schreiben
        parser.write(gameRoot, outputFile);
    }

}
