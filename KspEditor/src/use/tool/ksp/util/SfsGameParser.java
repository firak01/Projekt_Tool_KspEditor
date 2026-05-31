package use.tool.ksp.util;

import java.io.*;
import java.util.*;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import use.tool.ksp.object.*;



/** 
 * 
 * @author Fritz Lindhauer (featuring ChatGpt)
 *
 */
public class SfsGameParser extends AbstractSfsParser{
	
	public SfsGameParser() {
		super();
	}
	
	public SfsGameParser(File objFile) {
		super(objFile);
	}
	
	
	public FlightStateMatch parse() throws IOException, ExceptionZZZ{
		File objFile = this.getFile();
		return SfsGameParser.parse(objFile);
	}
	
    public static FlightStateMatch parse(File saveFile) throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader(saveFile));

        List<String> allLines = new ArrayList<String>();

        String line;
        while ((line = br.readLine()) != null) {
            allLines.add(line);
        }
        br.close();

        return parseFlightState(allLines);
    }
    
    /** FLIGHTSTATE Detection
     *  Findet den kompletten FLIGHTSTATE Block.
     * @param lines
     * @return
     */
    private static FlightStateMatch parseFlightState(List<String> lines) {

        FlightStateMatch fs = null;

        ParseState state = ParseState.OUTSIDE;

        int brace = 0;

        List<String> fsLines = new ArrayList<String>();

        for (int i = 0; i < lines.size(); i++) {

            String raw = lines.get(i);
            String t = raw.trim();

            if (state == ParseState.OUTSIDE) {

                if ("FLIGHTSTATE".equals(t)) {
                	fs = new FlightStateMatch();
                	fs.setStartLine(i);
                	brace = 0;
                    state = ParseState.IN_FLIGHTSTATE;
                }
            }

            if (state == ParseState.IN_FLIGHTSTATE) {

                fsLines.add(raw);

                brace = updateBrace(brace, raw);

                if (brace == 0 && fsLines.size() > 1) {
                    fs.setLines(fsLines);
                    fs.setEndLine(i);
                    
                    // RESETTE ERST JETZT !!!
					state = ParseState.OUTSIDE; //Zwar nicht mehr notwendig, aber der Vollständigkeit halber.
                    
                    return fs;
                }
            }
        }

        throw new IllegalStateException("FLIGHTSTATE nicht gefunden");
    }
    
    
   
    
    
    /** Vessel-Level State Machine
     * @param fs
     * @param targetName
     * @return
     * @throws ExceptionZZZ 
     */
    public static List<VesselMatch> parseVessels(FlightStateMatch fs) throws ExceptionZZZ {
		
		List<VesselMatch> result = new ArrayList<VesselMatch>();
		
		List<String> lines = fs.getLines();
		
		ParseState state = ParseState.OUTSIDE;
		
		VesselMatch current = null;
		int brace = 0;
		int partBrace = 0;
		
		boolean inFlightStateRoot = true;
		boolean inPart = false;
		
		for (int i = 0; i < lines.size(); i++) {
			
			String raw = lines.get(i);
			String t = raw.trim();
			
			// --------------------------
			// VESSEL START
			// --------------------------
			if (state == ParseState.OUTSIDE) {
			
				if (isRealVesselStart(lines, i)) {
				
					current = new VesselMatch();
					current.setVesselStartLine(i);
					
					state = ParseState.IN_VESSEL;
					brace = 1;
					inPart = false;
				}
			}//end if (state == ParseState.OUTSIDE) {
			
			// --------------------------
			// VESSEL PARSING
			// --------------------------
			if (state == ParseState.IN_VESSEL) {
			
				current.getVesselLines().add(raw);
				
				brace = updateBrace(brace, raw);
				
				// PART tracking
				if ("PART".equals(t)) {

				    inPart = true;
				    partBrace = 0;
				}
				
				// WÄHREND PART
				if (inPart) {

				    partBrace = updateBrace(partBrace, raw);

				    if (partBrace == 0) {
				        inPart = false;
				    }
				}
				
				//Merke: Reihenfolge entspricht der Reihenfolge in der VESSEL Struktur
				
				// --------------------------
				// Vessel Identity (ROOT ONLY!)
				// --------------------------
				if (!inPart && t.startsWith("pid = ")) {				
					if (StringZZZ.isEmptyTrimmed(current.getVesselPid())) {				
						current.setVesselPID(
								t.substring("pid = ".length()).trim()
						);
					}
				}
				
				if (!inPart && t.startsWith("persistentId = ")) {				
					if (StringZZZ.isEmptyTrimmed(current.getVesselPersistenId())) {				
						current.setVesselPersistentId(
								t.substring("persistentId = ".length()).trim()
						);
					}
				}
				
				// --------------------------
				// Vessel Name (ROOT ONLY!)
				// --------------------------
				if (!inPart && t.startsWith("name = ")) {				
					if (StringZZZ.isEmptyTrimmed(current.getVesselName())) {				
						current.setVesselName(
								t.substring("name = ".length()).trim()
						);
					}
				}
				
				
				// --------------------------
				// END VESSEL
				// --------------------------
				if (brace == 0) {
					//System.out.println("wirklich am VESSEL ende?");
					
					current.setVesselEndLine(i);
					
					result.add(current);
					
					// RESETTE ERST JETZT !!!
					state = ParseState.OUTSIDE;
					current = null;
				}else if(brace == 1) { //merke wir fangen mit brace = 1 an!!!
					if(!StringZZZ.isEmpty(current.getVesselName())) {
						//System.out.println("wirklich kurz vor VESSEL ende?");
						
						current.setVesselEndLine(i);						
						result.add(current);

						// RESETTE ERST JETZT !!!
						state = ParseState.OUTSIDE;
						current = null;
					}else {
						//System.out.println("wirklich kurz nach VESSEL anfang?");
					}
				}//end if (brace....							
			}//end if (state == ParseState.IN_VESSEL) {
		}//end for
		
		
	return result;
	}
    
   
    
}