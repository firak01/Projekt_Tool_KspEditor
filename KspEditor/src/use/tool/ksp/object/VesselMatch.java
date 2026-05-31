package use.tool.ksp.object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import use.tool.ksp.util.VesselIdentity;

public class VesselMatch {

	private VesselIdentity vesselIdentity;
    private String vesselName;

    private int vesselStartLine_inFlightstate;
    private int vesselEndLine_inFlightstate;

    private int vesselStartLine_inFile;
    private int vesselEndLine_inFile;
    
    private List<String> vesselLines = new ArrayList<String>();

    public VesselMatch() {

    }
    
    public VesselIdentity getVesselIdentityObject() {
    	if( this.vesselIdentity == null) {
    		this.vesselIdentity = new VesselIdentity();
    	}
    	return this.vesselIdentity;
    }
    public void setVesselIdentityObject(VesselIdentity objVesselIdentity) {
    	this.vesselIdentity = objVesselIdentity;
    }
    
    public String getVesselPid() {
    	return this.getVesselIdentityObject().getPid();
    }
    public void setVesselPID(String sPID) {
    	this.getVesselIdentityObject().setPid(sPID);
    }
    
    public String getVesselPersistenId() {
    	return this.getVesselIdentityObject().getPersistenId();
    }
    public void setVesselPersistentId(String sPersistentId) {
    	this.getVesselIdentityObject().setPersistentId(sPersistentId);
    }
    
    //+++++++++++++++++++++++++++++++++++++++++++
    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    //+++++++++++++++++++++++++++++++++++++++++++
    public int getVesselStartLine_inFlightstate() {
        return vesselStartLine_inFlightstate;
    }

    public void setVesselStartLine_inFlightstate(int vesselStartLine) {
        this.vesselStartLine_inFlightstate = vesselStartLine;
    }

    public int getVesselEndLine_inFlightstate() {
        return vesselEndLine_inFlightstate;
    }

    public void setVesselEndLine_inFlightstate(int vesselEndLine) {
       this.vesselEndLine_inFlightstate = vesselEndLine;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++
    public int getVesselStartLine_inFile() {
        return vesselStartLine_inFile;
    }

    public void setVesselStartLine_inFile(int vesselStartLine) {
        this.vesselStartLine_inFile = vesselStartLine;
    }

    public int getVesselEndLine_inFile() {
        return vesselEndLine_inFile;
    }

    public void setVesselEndLine_inFile(int vesselEndLine) {

       this.vesselEndLine_inFile = vesselEndLine;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++

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


