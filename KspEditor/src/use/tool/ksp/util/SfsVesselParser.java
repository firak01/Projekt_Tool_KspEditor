package use.tool.ksp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zBasic.util.file.FileTextUtilZZZ;
import use.tool.ksp.object.FlightstateMatch;
import use.tool.ksp.object.VesselMatch;
import use.tool.ksp.util.ISfsParser.ParseState;

public class SfsVesselParser extends AbstractSfsParser{
	public SfsVesselParser() {
		super();
	}
	
	public SfsVesselParser(File objFile) {
		super(objFile);
	}
	
	public VesselMatch parse() throws IOException, ExceptionZZZ {
		File objFile = this.getFile();
		return SfsVesselParser.parse(objFile);
		
	}
	
	public static VesselMatch parse(File saveFile) throws IOException, ExceptionZZZ {
        List<String> allLines = FileTextUtilZZZ.readFileToList(saveFile);
        return parseVessel(allLines);
    }
	
	public static VesselMatch parseVessel(List<String> lines) throws ExceptionZZZ {
		VesselMatch objReturn = null;
		main:{
			if(lines==null) break main;
			
			ParseState state = ParseState.OUTSIDE;
		
			int brace = 0;
			int partBrace = 0;
			
			boolean inPart = false;
			
			for (int i = 0; i < lines.size(); i++) {
				
				String raw = lines.get(i);
				String t = raw.trim();
				
				// --------------------------
				// VESSEL START
				// --------------------------
				if (state == ParseState.OUTSIDE) {
				
					if (isRealVesselStart(lines, i)) {
					
						objReturn = new VesselMatch();
						objReturn.setVesselStartLine_inFile(i);
						
						state = ParseState.IN_VESSEL;
						brace = 1;
						inPart = false;
					}
				}//end if (state == ParseState.OUTSIDE) {
				
				// --------------------------
				// VESSEL PARSING
				// --------------------------
				if (state == ParseState.IN_VESSEL) {
				
					objReturn.getVesselLines().add(raw);
					
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
						if (StringZZZ.isEmptyTrimmed(objReturn.getVesselPid())) {				
							objReturn.setVesselPID(
									t.substring("pid = ".length()).trim()
							);
						}
					}
					
					if (!inPart && t.startsWith("persistentId = ")) {				
						if (StringZZZ.isEmptyTrimmed(objReturn.getVesselPersistenId())) {				
							objReturn.setVesselPersistentId(
									t.substring("persistentId = ".length()).trim()
							);
						}
					}
					
					// --------------------------
					// Vessel Name (ROOT ONLY!)
					// --------------------------
					if (!inPart && t.startsWith("name = ")) {				
						if (StringZZZ.isEmptyTrimmed(objReturn.getVesselName())) {				
							objReturn.setVesselName(
									t.substring("name = ".length()).trim()
							);
						}
					}
					
					
					// --------------------------
					// END VESSEL
					// --------------------------
					if (brace == 0) {
						//System.out.println("wirklich am VESSEL ende?");
						
						objReturn.setVesselEndLine_inFile(i);
						
						//result.add(current);
						
						// RESETTE ERST JETZT !!!
						state = ParseState.OUTSIDE; //zwar nicht notwendig, aber der Vollständigkeit halber!!!
					}else if(brace == 1) { //merke wir fangen mit brace = 1 an!!!
						if(!StringZZZ.isEmpty(objReturn.getVesselName())) {
							//System.out.println("wirklich kurz vor VESSEL ende?");
							
							objReturn.setVesselEndLine_inFlightstate(i);						
							//result.add(current);
	
							// RESETTE ERST JETZT !!!
							state = ParseState.OUTSIDE;//zwar nicht notwendig, aber der Vollständigkeit halber!!!						
						}else {
							//System.out.println("wirklich kurz nach VESSEL anfang?");
						}
					}//end if (brace....							
				}//end if (state == ParseState.IN_VESSEL) {
			}//end for
		
		}//end main:		
		return objReturn;
	}
	
	
}
