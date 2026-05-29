package use.tool.ksp.object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VesselMatch {

    private String vesselName;

    private int vesselStartLine;
    private int vesselEndLine;

    private List<String> vesselLines = new ArrayList<String>();

    public VesselMatch() {

    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public int getVesselStartLine() {
        return vesselStartLine;
    }

    public void setVesselStartLine(int vesselStartLine) {
        this.vesselStartLine = vesselStartLine;
    }

    public int getVesselEndLine() {
        return vesselEndLine;
    }

    public void setVesselEndLine(int vesselEndLine) {
        this.vesselEndLine = vesselEndLine;
    }

    public List<String> getVesselLines() {
        return vesselLines;
    }

    public void setVesselLines(List<String> vesselLines) {
        this.vesselLines = vesselLines;
    }

    /**
     * Anzahl der PART Blöcke im Vessel.
     */
    public int countParts() {

        int count = 0;

        for (String line : vesselLines) {

            String trimmed = line.trim();

            if ("PART".equals(trimmed)) {
                count++;
            }
        }

        return count;
    }

	/**
	 * Höchster verwendeter PART-Index
	 * innerhalb des Vessel.
	 *
	 * Entspricht:
	 * countParts() - 1
	 */
	public int findHighestPartIndex() {

	    int partCount = countParts();

	    if (partCount <= 0) {
	        return -1;
	    }

	    return partCount - 1;
	}
    		

    /**
     * Debug-Ausgabe des kompletten Vessel-Blocks.
     */
    public void debugWriteToFile(File outputDirectory)
            throws IOException {

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        String safeName =
                vesselName.replaceAll("[^a-zA-Z0-9_\\-]", "_");

        File outFile =
                new File(outputDirectory,
                        "VESSEL_" + safeName + ".sfs");

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(outFile));

        try {

            for (String line : vesselLines) {

                writer.write(line);
                writer.newLine();
            }

        } finally {

            writer.close();
        }

        System.out.println(
                "DEBUG Vessel geschrieben: "
                        + outFile.getAbsolutePath());
    }
}


