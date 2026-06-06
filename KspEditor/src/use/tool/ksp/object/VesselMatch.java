package use.tool.ksp.object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.datatype.dateTime.DateTimeZZZ;
import use.tool.ksp.util.SfsVesselParser;
import use.tool.ksp.util.VesselIdentity;

public class VesselMatch {

	private VesselIdentity vesselIdentity;
    private String sVesselName;

    private int iVesselStartLine_inFlightstate=-1;
    private int iVesselEndLine_inFlightstate=-1;

    private int iVesselStartLine_inFile=-1;
    private int iVesselEndLine_inFile=-1;
    
    private int iVesselPartStartLine=-1;
    private int iVesselPartEndLine=-1;
    
    private int iVesselPartStartLine_inFlightstate=-1;
    private int iVesselPartEndLine_inFlightstate=-1;

    private int iVesselPartStartLine_inFile=-1;
    private int iVesselPartEndLine_inFile=-1;
    
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
        return sVesselName;
    }

    public void setVesselName(String vesselName) {
        this.sVesselName = vesselName;
    }

    //+++++++++++++++++++++++++++++++++++++++++++
    public int getVesselPartStartLine() {
        return iVesselPartStartLine;
    }

    public void setVesselPartStartLine(int vesselPartStartLine) {
        this.iVesselPartStartLine = vesselPartStartLine;
    }
    
    public int getVesselPartEndLine() {
        return iVesselPartEndLine;
    }

    public void setVesselPartEndLine(int vesselPartEndLine) {
        this.iVesselPartEndLine = vesselPartEndLine;
    }
    
    public void updateVesselPartStartLine(int iVesselPartStartLineNew) {
    	int iVesselPartStartLine = this.getVesselPartStartLine();
    	if(iVesselPartStartLine>iVesselPartStartLineNew | iVesselPartStartLine==-1) {
    		this.setVesselPartStartLine(iVesselPartStartLineNew);
    	}
    }
    
    public void updateVesselPartEndLine(int iVesselPartEndLineNew) {
    	int iVesselPartEndLine = this.getVesselPartEndLine();
    	if(iVesselPartEndLine<iVesselPartEndLineNew | iVesselPartEndLine==-1) {
    		this.setVesselPartEndLine(iVesselPartEndLineNew);
    	}
    }
    
    //+++++++++++++++++++++++++++++++++++++++++++
    public int getVesselPartStartLine_inFlightstate() {
        return iVesselPartStartLine_inFlightstate;
    }

    public void setVesselPartStartLine_inFlightstate(int vesselPartStartLine) {
        this.iVesselPartStartLine_inFlightstate = vesselPartStartLine;
    }

    public int getVesselPartEndLine_inFlightstate() {
        return iVesselPartEndLine_inFlightstate;
    }

    public void setVesselPartEndLine_inFlightstate(int vesselPartEndLine) {
       this.iVesselPartEndLine_inFlightstate = vesselPartEndLine;
    }
    
    public void updateVesselPartStartLine_inFlightstate(int iVesselPartStartLineNew) {
    	int iVesselPartStartLine = this.getVesselPartStartLine_inFlightstate();
    	if(iVesselPartStartLine>iVesselPartStartLineNew | iVesselPartStartLine==-1) {
    		this.setVesselPartStartLine_inFlightstate(iVesselPartStartLineNew);
    	}
    }
    
    public void updateVesselPartEndLine_inFlightstate(int iVesselPartEndLineNew) {
    	int iVesselPartEndLine = this.getVesselPartEndLine_inFlightstate();
    	if(iVesselPartEndLine<iVesselPartEndLineNew | iVesselPartEndLine==-1) {
    		this.setVesselPartEndLine_inFlightstate(iVesselPartEndLineNew);
    	}
    }
    
    //++++++++++++++++++++++++++++++++++++++++++
    public int getVesselPartStartLine_inFile() {
        return iVesselPartStartLine_inFile;
    }

    public void setVesselPartStartLine_inFile(int vesselPartStartLine) {
        this.iVesselPartStartLine_inFile = vesselPartStartLine;
    }

    public int getVesselPartEndLine_inFile() {
        return iVesselPartEndLine_inFile;
    }

    public void setVesselPartEndLine_inFile(int vesselPartEndLine) {
       this.iVesselPartEndLine_inFile = vesselPartEndLine;
    }
    
    public void updateVesselPartStartLine_inFile(int iVesselPartStartLineNew) {
    	int iVesselPartStartLine = this.getVesselPartStartLine_inFile();
    	if(iVesselPartStartLine>iVesselPartStartLineNew | iVesselPartStartLine==-1) {
    		this.setVesselPartStartLine_inFile(iVesselPartStartLineNew);
    	}
    }
    
    public void updateVesselPartEndLine_inFile(int iVesselPartEndLineNew) {
    	int iVesselPartEndLine = this.getVesselPartEndLine_inFile();
    	if(iVesselPartEndLine<iVesselPartEndLineNew | iVesselPartEndLine==-1) {
    		this.setVesselPartEndLine_inFile(iVesselPartEndLineNew);
    	}
    }

    //+++++++++++++++++++++++++++++++++++++++++++
    public int getVesselStartLine_inFlightstate() {
        return iVesselStartLine_inFlightstate;
    }

    public void setVesselStartLine_inFlightstate(int vesselStartLine) {
        this.iVesselStartLine_inFlightstate = vesselStartLine;
    }

    public int getVesselEndLine_inFlightstate() {
        return iVesselEndLine_inFlightstate;
    }

    public void setVesselEndLine_inFlightstate(int vesselEndLine) {
       this.iVesselEndLine_inFlightstate = vesselEndLine;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++
    public int getVesselStartLine_inFile() {
        return iVesselStartLine_inFile;
    }

    public void setVesselStartLine_inFile(int vesselStartLine) {
        this.iVesselStartLine_inFile = vesselStartLine;
    }

    public int getVesselEndLine_inFile() {
        return iVesselEndLine_inFile;
    }

    public void setVesselEndLine_inFile(int vesselEndLine) {

       this.iVesselEndLine_inFile = vesselEndLine;
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
        int i = 0;
        for (String line : vesselLines) {
            //Wg. KIS - Mod und vorhanden Containern, die PARTS beinhalten, reicht das nicht aus.
            //String trimmed = line.trim();
            //if ("PART".equals(trimmed)) {
        	
            if(SfsVesselParser.isRealVesselPartStart(vesselLines, i)) {
                count++;
            }
            i++;
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
     * @throws ExceptionZZZ 
     */
    public void debugWriteToFile(File outputDirectory)
            throws IOException, ExceptionZZZ {

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        String safeName =
                sVesselName.replaceAll("[^a-zA-Z0-9_\\-]", "_");

        Long lTimestamp = DateTimeZZZ.computeTimestamp();
        String sDateTime = DateTimeZZZ.computeTimestampStringFormatedDefault(lTimestamp);
        
        File outFile =
                new File(outputDirectory,
                        "VESSEL_" + safeName + "_" + sDateTime + ".sfs");

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


