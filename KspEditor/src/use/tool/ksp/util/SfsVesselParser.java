package use.tool.ksp.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.FileTextUtilZZZ;
import use.tool.ksp.object.VesselMatch;

public class SfsVesselParser extends AbstractSfsParser{
	public SfsVesselParser() {
		super();
	}
	
	public SfsVesselParser(File objFile) throws ExceptionZZZ {
		super(objFile);
	}
	
	public VesselMatch parse() throws ExceptionZZZ {
		File objFile = this.getFile();
		return SfsVesselParser.parse(objFile);		
	}
	
	public static VesselMatch parse(File saveFile) throws ExceptionZZZ {
        List<String> allLines = FileTextUtilZZZ.readFileToList(saveFile);
        return parseVessel(allLines);
    }
	
	public static VesselMatch parseVessel(List<String> lines) throws ExceptionZZZ {
		VesselMatch objReturn = null;
		main:{
			if(lines==null) {			
				ExceptionZZZ ez = new ExceptionZZZ("No lines provided.", iERROR_PARAMETER_EMPTY, SfsVesselParser.class, ReflectCodeZZZ.getMethodCurrentName());
				throw ez;
			}
			
			ParseState state = ParseState.OUTSIDE;
		
			int brace = 0;
			int partBrace = 0;
			
			boolean inPart = false;
			
			for (int i = 0; i < lines.size(); i++) {
				
				String raw = lines.get(i);
				String t = raw.trim();
				
//				if(i==2390) {
//					System.out.println("DEBUG BREAK 1");
//				}
//				if(t.equals("name = KKAOSS.KIS.FuelTank")) {
//					System.out.println("DEBUG BREAK 2");
//				}
				
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
					//Diese einfache Abfrage reicht aber nicht aus if ("PART".equals(t)) {
					if(isRealVesselPartStart(lines, i)) {
						int iVesselStart = i;
						if(iVesselStart >= 0) {
							int iPartStart = i;
							objReturn.updateVesselPartStartLine(iPartStart);
						}
						
						int iVesselStartInFile = objReturn.getVesselStartLine_inFile();
						if(iVesselStartInFile>=0) {
							int iPartInFileStart = iVesselStartInFile + i;
							objReturn.updateVesselPartStartLine_inFile(iPartInFileStart);
						}
						
						int iVesselStartInFlightState = objReturn.getVesselStartLine_inFlightstate();
						if(iVesselStartInFlightState>=0) {
							int iPartStart = iVesselStartInFlightState + i;
							objReturn.updateVesselPartStartLine_inFlightstate(iPartStart);
						}
						
					    inPart = true;
					    partBrace = 1;
					}
					
					// WÄHREND PART
					if (inPart) {						
						if (partBrace >= 1) {
							int iVesselEnd = i;
							if(iVesselEnd>=0) {
								int iPartEnd = i;
								objReturn.updateVesselPartEndLine(iPartEnd);
							}
					        
							int iVesselStartInFile = objReturn.getVesselStartLine_inFile();
							if(iVesselStartInFile>=0) {
								int iPartInFileEnd = iVesselStartInFile + i;
								objReturn.updateVesselPartEndLine_inFile(iPartInFileEnd);
							}
							
							int iVesselStartInFlightState = objReturn.getVesselStartLine_inFlightstate();
							if(iVesselStartInFlightState>=0) {
								int iPartEnd = iVesselStartInFlightState + i;
								objReturn.updateVesselPartEndLine_inFlightstate(iPartEnd);
							}		

							partBrace = updateBrace(partBrace, raw);	
							
							if (partBrace <= 1 && !t.equals("PART")) { //wir starten mit 1, die gleiche Zeile soll aber ausgeschlossen werden.
							       inPart = false;					        					        
							 }
						}
						
						
					}
					
					// --------------------------
					// Vessel Identity (ROOT ONLY!)
					// --------------------------
					
					//Merke: Reihenfolge entspricht der Reihenfolge in der VESSEL Struktur									
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
							
							objReturn.setVesselEndLine_inFile(i);
							
							//Hier gibt es keinen Flightstate, der übergeben wird.
							//objReturn.setVesselEndLine_inFlightstate(i);						
							//result.add(current);
	
							// RESETTE ERST JETZT !!!
							state = ParseState.OUTSIDE;//zwar nicht notwendig, aber der Vollständigkeit halber!!!						
						}else {
							//System.out.println("wirklich kurz nach VESSEL anfang?");
						}
					}//end if (brace....							
				}//end if (state == ParseState.IN_VESSEL) {
			}//end for
			if(objReturn==null) {
				IllegalStateException e =  new IllegalStateException("VESSEL nicht gefunden");
				ExceptionZZZ ez = new ExceptionZZZ(e);
				throw ez;
			}
		}//end main:		
		return objReturn;
	}
	
	
}
