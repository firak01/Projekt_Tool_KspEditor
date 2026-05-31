package use.tool.ksp;

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
import use.tool.ksp.util.SfsGameParser;

public class VesselFinder extends AbstractVesselTool{

   
	protected VesselFinder(SfsGameParser parser) {
		super(parser);
	}

	
	/**
	 * Findet genau EIN Vessel über den Namen.
	 * @throws ExceptionZZZ 
	 */
    public static VesselMatch findFirstVesselByName(SfsGameParser objParser, String targetVesselName)
            throws IOException, ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{
	        FlightStateMatch fs = objParser.parse();
	        objReturn = findFirstVesselByName(fs, targetVesselName);
    	}//end main:
        return objReturn;
    }
    
	/**
	 * Findet genau EIN Vessel über den Namen.
	 * @throws ExceptionZZZ 
	 */
    public static VesselMatch findFirstVesselByName(File file, String targetVesselName)
            throws IOException, ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{
	        FlightStateMatch fs = SfsGameParser.parse(file);
	        objReturn = findFirstVesselByName(fs, targetVesselName);
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
	        FlightStateMatch fs = SfsGameParser.parse(file);
	        objReturn = findSingleVesselByName(fs,targetVesselName) ;
    	}//end main:
        return objReturn;
    }
    
    /**
   	 * Findet genau EIN Vessel über den Namen.
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findSingleVesselByName(FlightStateMatch fs,String targetVesselName)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{       	
   	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
   	        ArrayList<VesselMatch>listaVessel = new ArrayList<VesselMatch>();
   	        for (VesselMatch v : vessels) {
   	            if (targetVesselName.equals(v.getVesselName())) {
   	                listaVessel.add(v);
   	            }
   	        }
   	        
   	        if (listaVessel.size() != 1) {
   	            throw new IllegalStateException("No Vessel with the name '" + targetVesselName + "' or more than one Vessel. Number of vessels found: " + vessels.size() );
   	        }
   	        
   	        objReturn = listaVessel.get(0);
       	}//end main:
           return objReturn;
       }
    
       /**
   	 * Findet genau EIN Vessel über den Namen.
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findFirstVesselByName(FlightStateMatch fs, String targetVesselName)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{       	
   	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
   	        for (VesselMatch v : vessels) {
   	            if (targetVesselName.equals(v.getVesselName())) {
   	                objReturn = v;
   	                break;
   	            }
   	        }
       	}//end main:
           return objReturn;
       }
    
    
    //###################################################
    //########## PersistentId
    //###################################################
   /**
 	 * Findet genau EIN Vessel über die Pid.
 	 * @throws ExceptionZZZ 
 	 */
     public static VesselMatch findFirstVesselByPersistentId(SfsGameParser objParser, String targetVesselPersistentId)
             throws IOException, ExceptionZZZ {
     	VesselMatch objReturn = null;
     	main:{
 	        FlightStateMatch fs = objParser.parse();
 	        objReturn = findFirstVesselByPersistentId(fs, targetVesselPersistentId); 	       
     	}//end main:
         return objReturn;
     }
         
    /**
	 * Findet genau EIN Vessel über die PersistentId.
	 * @throws ExceptionZZZ 
	 */
    public static VesselMatch findFirstVesselByPersistentId(File file, String targetVesselPersistentId)
            throws IOException, ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{    	
	        FlightStateMatch fs = SfsGameParser.parse(file);
	        objReturn = findFirstVesselByPersistentId(fs, targetVesselPersistentId); 
    	}//end main:
        return objReturn;
    }
    
    /**
   	 * Findet genau EIN Vessel über die PersistentId
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findSingleVesselByPersistentId(File file, String targetVesselPersistentId)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{       	
   	        FlightStateMatch fs = SfsGameParser.parse(file);
   	        objReturn = findSingleVesselByPersistentId(fs, targetVesselPersistentId);
       	}//end main:
           return objReturn;
       }
       
       
       public static VesselMatch findSingleVesselByPersistentId(FlightStateMatch fs, String targetVesselPersistentId)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{
   	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
   	        ArrayList<VesselMatch>listaVessel = new ArrayList<VesselMatch>();
   	        for (VesselMatch v : vessels) {
   	            if (targetVesselPersistentId.equals(v.getVesselPersistenId())) {
   	                listaVessel.add(v);
   	            }
   	        }
   	        
   	        if (listaVessel.size() != 1) {   	            
   	            throw new IllegalStateException("No Vessel with the persistentId '" + targetVesselPersistentId + "' or more than one Vessel. Number of vessels found: " + vessels.size() );
   	        }
   	        
   	        objReturn = listaVessel.get(0);
       	}//end main:
           return objReturn;
       }
       
       public static VesselMatch findFirstVesselByPersistentId(FlightStateMatch fs, String targetVesselPersistentId)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{
       	    List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
   	        for (VesselMatch v : vessels) {
   	            if (targetVesselPersistentId.equals(v.getVesselPersistenId())) {
   	                objReturn = v;
   	                break;
   	            }
   	        }
       	}//end main:
           return objReturn;
       }
    
       
       
    //###################################################
    //########## Pid
    //###################################################
    /**
  	 * Findet genau EIN Vessel über die Pid.
  	 * @throws ExceptionZZZ 
  	 */
      public static VesselMatch findFirstVesselByPid(SfsGameParser objParser, String targetVesselPid)
              throws IOException, ExceptionZZZ {
      	VesselMatch objReturn = null;
      	main:{      	
  	        FlightStateMatch fs = objParser.parse();
  	        objReturn = findFirstVesselByPid(fs, targetVesselPid);  	       
      	}//end main:
          return objReturn;
      }
       
     /**
   	 * Findet genau EIN Vessel über die Pid.
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findFirstVesselByPid(File file, String targetVesselPid)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{       	
   	        FlightStateMatch fs = SfsGameParser.parse(file);
   	        objReturn = findFirstVesselByPid(fs, targetVesselPid);
       	}//end main:
           return objReturn;
       }
       
       /**
  	 * Findet genau EIN Vessel über die Pid
  	 * @throws ExceptionZZZ 
  	 */
      public static VesselMatch findSingleVesselByPid(File file, String targetVesselPid)
              throws IOException, ExceptionZZZ {
      	VesselMatch objReturn = null;
      	main:{      	
  	        FlightStateMatch fs = SfsGameParser.parse(file);
  	        objReturn = findSingleVesselByPid(fs, targetVesselPid);
      	}//end main:
          return objReturn;
      }
      
      /**
 	 * Findet genau EIN Vessel über die Pid.
 	 * @throws ExceptionZZZ 
 	 */
     public static VesselMatch findFirstVesselByPid(FlightStateMatch fs, String targetVesselPid)
             throws IOException, ExceptionZZZ {
     	VesselMatch objReturn = null;
     	main:{         	
 	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
 	        for (VesselMatch v : vessels) {
 	            if (targetVesselPid.equals(v.getVesselPid())) {
 	                objReturn = v;
 	                break;
 	            }
 	        }
     	}//end main:
         return objReturn;
     }
         
     /**
   	 * Findet genau EIN Vessel über die Pid
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findSingleVesselByPid(FlightStateMatch fs, String targetVesselPid)
               throws IOException, ExceptionZZZ {
       	VesselMatch objReturn = null;
       	main:{       	
   	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
   	        ArrayList<VesselMatch>listaVessel = new ArrayList<VesselMatch>();
   	        for (VesselMatch v : vessels) {
   	            if (targetVesselPid.equals(v.getVesselPid())) {
   	                listaVessel.add(v);
   	            }
   	        }
   	        
   	        if (listaVessel.size() != 1) {   	            
   	            throw new IllegalStateException("No Vessel with the pid '" + targetVesselPid + "' or more than one Vessel. Number of vessels found: " + vessels.size() );
   	        }
   	        
   	        objReturn = listaVessel.get(0);
       	}//end main:
           return objReturn;
       }

    
    /**
   * Sucht Vessel innerhalb des FLIGHTSTATE.
   * @throws ExceptionZZZ 
   */
    public static List<VesselMatch> findAllVesselsByName(
            File saveFile,
            String targetVesselName)
            throws IOException, ExceptionZZZ {

        FlightStateMatch flightState =
                SfsGameParser.parse(saveFile);

        List<VesselMatch> allVessels =
                SfsGameParser.parseVessels(flightState);

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
