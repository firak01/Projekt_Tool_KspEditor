package use.tool.ksp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import use.tool.ksp.object.FlightStateMatch;
import use.tool.ksp.object.VesselMatch;

public class VesselFinder {

    /**
     * Findet den kompletten FLIGHTSTATE Block.
     */
//    public static FlightStateMatch findFlightState(
//            File saveFile)
//            throws IOException {
//
//        BufferedReader reader =
//                new BufferedReader(
//                        new FileReader(saveFile));
//
//        try {
//
//            List<String> allLines =
//                    new ArrayList<String>();
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//
//                allLines.add(line);
//            }
//
//            for (int i = 0; i < allLines.size(); i++) {
//
//                String trimmed =
//                        allLines.get(i).trim();
//
//                if ("FLIGHTSTATE".equals(trimmed)) {
//
//                    int braceCounter = 0;
//
//                    boolean started = false;
//
//                    List<String> flightLines =
//                            new ArrayList<String>();
//
//                    for (int j = i;
//                         j < allLines.size();
//                         j++) {
//
//                        String currentLine =
//                                allLines.get(j);
//
//                        flightLines.add(currentLine);
//
//                        for (int c = 0;
//                             c < currentLine.length();
//                             c++) {
//
//                            char ch =
//                                    currentLine.charAt(c);
//
//                            if (ch == '{') {
//
//                                braceCounter++;
//                                started = true;
//                            }
//
//                            if (ch == '}') {
//
//                                braceCounter--;
//                            }
//                        }
//
//                        if (started && braceCounter == 0) {
//
//                            FlightStateMatch match =
//                                    new FlightStateMatch();
//
//                            match.setStartLine(i);
//                            match.setEndLine(j);
//                            match.setLines(flightLines);
//
//                            return match;
//                        }
//                    }
//                }
//            }
//
//            throw new IllegalStateException(
//                    "FLIGHTSTATE Block nicht gefunden.");
//
//        } finally {
//
//            reader.close();
//        }
//    }

//    /**
//     * Findet genau EIN Vessel über den Namen.
//     * @throws ExceptionZZZ 
//     */
//    public static VesselMatch findVesselByName(
//            File saveFile,
//            String targetVesselName)
//            throws IOException, ExceptionZZZ {
//
//        FlightStateMatch flightStateMatch =
//                findFlightState(saveFile);
//
//        List<VesselMatch> matches =
//                findAllVesselsByName(
//                        flightStateMatch,
//                        targetVesselName);
//
//        if (matches.isEmpty()) {
//
//            throw new IllegalStateException(
//                    "Kein Vessel gefunden mit Name: "
//                            + targetVesselName);
//        }
//
//        if (matches.size() > 1) {
//
//            throw new IllegalStateException(
//                    "Mehrere Vessel gefunden mit Name: "
//                            + targetVesselName
//                            + " Anzahl="
//                            + matches.size());
//        }
//
//        return matches.get(0);
//    }
    
    
	/**
	 * Findet genau EIN Vessel über den Namen.
	 * @throws ExceptionZZZ 
	 */
    public static VesselMatch findFirstVesselByName(File file, String targetVesselName)
            throws IOException, ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{
    	
	        FlightStateMatch fs = SfsParser.parse(file);
	
	        List<VesselMatch> vessels = SfsParser.parseVessels(fs);
	        for (VesselMatch v : vessels) {
	            if (targetVesselName.equals(v.getVesselName())) {
	                objReturn = v;
	                break;
	            }
	        }
    	}//end main:
        return objReturn;
    }
    
    /**
	 * Findet genau EIN Vessel über den Namen.
	 * @throws ExceptionZZZ 
	 */
    public static VesselMatch findSingleVesselByName(File file, String targetVesselName)
            throws IOException, ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{
    	
	        FlightStateMatch fs = SfsParser.parse(file);
	
	        List<VesselMatch> vessels = SfsParser.parseVessels(fs);
	        ArrayList<VesselMatch>listaVessel = new ArrayList<VesselMatch>();
	        for (VesselMatch v : vessels) {
	            if (targetVesselName.equals(v.getVesselName())) {
	                listaVessel.add(v);
	            }
	        }
	        
	        if (listaVessel.size() != 1) {
	            throw new IllegalStateException("No Vessel with the name '" + targetVesselName + "' or more than one Vessel, number of vessels found: " + vessels.size() );
	        }
	        
	        objReturn = listaVessel.get(0);
    	}//end main:
        return objReturn;
    }
    

//    /**
//     * Sucht Vessel innerhalb des FLIGHTSTATE.
//     * @throws ExceptionZZZ 
//     */
//    public static List<VesselMatch> findAllVesselsByName(
//            FlightStateMatch flightStateMatch,
//            String targetVesselName) throws ExceptionZZZ {
//
//        List<VesselMatch> result =
//                new ArrayList<VesselMatch>();
//
//        List<String> allLines =
//                flightStateMatch.getLines();
//
//        for (int i = 0; i < allLines.size(); i++) {
//
//            if (isRealVesselStart(allLines, i)) {
//
//                VesselMatch match =
//                        parseVessel(allLines, i);
//
//                if (match != null) {
//
//                    if (targetVesselName.equals(
//                            match.getVesselName())) {
//
//                        result.add(match);
//                    }
//
//                    i = match.getVesselEndLine();
//                }
//            }
//        }
//
//        return result;
//    }
    
    /**
   * Sucht Vessel innerhalb des FLIGHTSTATE.
   * @throws ExceptionZZZ 
   */
    public static List<VesselMatch> findAllVesselsByName(
            File saveFile,
            String targetVesselName)
            throws IOException, ExceptionZZZ {

        FlightStateMatch flightState =
                SfsParser.parse(saveFile);

        List<VesselMatch> allVessels =
                SfsParser.parseVessels(flightState);

        List<VesselMatch> result =
                new ArrayList<VesselMatch>();

        for (VesselMatch v : allVessels) {

            if (targetVesselName.equals(v.getVesselName())) {
                result.add(v);
            }
        }

        return result;
    }
   
   

//    /**
//     * Parst EIN komplettes Vessel.
//     * @throws ExceptionZZZ 
//     */
//    private static VesselMatch parseVessel(
//            List<String> allLines,
//            int vesselStartIndex) throws ExceptionZZZ {
//
//        VesselMatch vessel =
//                new VesselMatch();
//
//        vessel.setVesselStartLine(
//                vesselStartIndex);
//
//        List<String> vesselLines =
//                new ArrayList<String>();
//
//        int braceCounter = 0;
//
//        boolean started = false;
//
//        for (int i = vesselStartIndex;
//             i < allLines.size();
//             i++) {
//
//            String line =
//                    allLines.get(i);
//
//            vesselLines.add(line);
//
//            String trimmed =
//                    line.trim();
//
//            for (int c = 0;
//                 c < line.length();
//                 c++) {
//
//                char ch =
//                        line.charAt(c);
//
//                if (ch == '{') {
//
//                    braceCounter++;
//                    started = true;
//                }
//
//                if (ch == '}') {
//
//                    braceCounter--;
//                }
//            }
//            
//            //Der Vesselname ist der erste name = innerhalb des VESSEL-Blocks. Andere sind z.B. PARTS-Namen.
//            String vesselName = getVesselNameReal(trimmed, vessel);
//            if(!StringZZZ.isEmptyNull(vesselName)) {
//            	vessel.setVesselName(vesselName);
//            }
//            
//
//            if (started && braceCounter == 0) {
//
//                vessel.setVesselEndLine(i);
//
//                vessel.setVesselLines(vesselLines);
//
//                return vessel;
//            }
//        }
//
//        return null;
//    }
    
//    private static String getVesselNameReal(String trimmed, VesselMatch vessel) throws ExceptionZZZ {
//    	String sReturn = null;
//    	main:{
//	    	 //Der Vesselname ist der erste name = innerhalb des VESSEL-Blocks. Andere sind z.B. PARTS-Namen.
//    		 //Wurde der VesselName schon einmal vergeben, dann ist es ein anderer. 
//    		if(!StringZZZ.isEmptyTrimmed(vessel.getVesselName())) break main;
//    		
//    		//Wurde noch kein VesselName vergeben, dann ist es ggfs. möglich einen zu finden.
//	        if (trimmed.startsWith("name = ")) {
//	        	sReturn =
//                        trimmed.substring(
//                                "name = ".length())
//                                .trim();
//	        	
//	        }
//    	}//end main:
//    	return sReturn;
//    }
}
