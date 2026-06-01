package use.tool.ksp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import use.tool.ksp.object.FlightstateMatch;
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
    public static VesselMatch findFirstVesselByName(SfsGameParser objParser, String targetVesselName) throws ExceptionZZZ {
    	VesselMatch objReturn = null;
    	main:{
	        FlightstateMatch fs = objParser.parse();
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
	        FlightstateMatch fs = SfsGameParser.parse(file);
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
	        FlightstateMatch fs = SfsGameParser.parse(file);
	        objReturn = findSingleVesselByName(fs,targetVesselName) ;
    	}//end main:
        return objReturn;
    }
    
    /**
   	 * Findet genau EIN Vessel über den Namen.
   	 * @throws ExceptionZZZ 
   	 */
	   public static VesselMatch findSingleVesselByName(FlightstateMatch fs,String targetVesselName) throws ExceptionZZZ {
	       	VesselMatch objReturn = null;
	       	main:{   
	       		if(fs==null) {
					ExceptionZZZ ez = new ExceptionZZZ( "FlightstateMatch-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;		 
				}
	       		if(StringZZZ.isEmptyTrimmed(targetVesselName)) {
	       			ExceptionZZZ ez = new ExceptionZZZ( "Name of vessel-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;
	       		}
	       		
	   	        List<VesselMatch> vessels = SfsGameParser.parseVessels(fs);
	   	        ArrayList<VesselMatch>listaVessel = new ArrayList<VesselMatch>();
	   	        for (VesselMatch v : vessels) {
	   	            if (targetVesselName.equals(v.getVesselName())) {
	   	                listaVessel.add(v);
	   	            }
	   	        }
	   	        
	   	        if (listaVessel.size() != 1) {
	   	            IllegalStateException e = new IllegalStateException("No Vessel with the name '" + targetVesselName + "' or more than one Vessel. Number of vessels found: " + vessels.size() );
	   	            ExceptionZZZ ez = new ExceptionZZZ(e);
	   	            throw ez;
	   	        }
	   	        
	   	        objReturn = listaVessel.get(0);
	   		}//end main:
	       	return objReturn;
	   }
    
       /**
   	 * Findet genau EIN Vessel über den Namen.
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findFirstVesselByName(FlightstateMatch fs, String targetVesselName)throws ExceptionZZZ {
	       	VesselMatch objReturn = null;
	       	main:{       	
	       		if(fs==null) {
					ExceptionZZZ ez = new ExceptionZZZ( "FlightstateMatch-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;		 
				}
	       		if(StringZZZ.isEmptyTrimmed(targetVesselName)) {
	       			ExceptionZZZ ez = new ExceptionZZZ( "Name of vessel-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;
	       		}
	       		
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
     public static VesselMatch findFirstVesselByPersistentId(SfsGameParser objParser, String targetVesselPersistentId) throws ExceptionZZZ {
     	VesselMatch objReturn = null;
     	main:{
 	        FlightstateMatch fs = objParser.parse();
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
	        FlightstateMatch fs = SfsGameParser.parse(file);
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
   	        FlightstateMatch fs = SfsGameParser.parse(file);
   	        objReturn = findSingleVesselByPersistentId(fs, targetVesselPersistentId);
       	}//end main:
           return objReturn;
       }
       
       
       public static VesselMatch findSingleVesselByPersistentId(FlightstateMatch fs, String targetVesselPersistentId)
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
       
       public static VesselMatch findFirstVesselByPersistentId(FlightstateMatch fs, String targetVesselPersistentId) throws ExceptionZZZ {
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
      public static VesselMatch findFirstVesselByPid(SfsGameParser objParser, String targetVesselPid) throws ExceptionZZZ {
	      	VesselMatch objReturn = null;
	      	main:{      
	      		if(objParser==null) {
					ExceptionZZZ ez = new ExceptionZZZ( "SfsGameParser-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;		 
				}
	      		
	  	        FlightstateMatch fs = objParser.parse();
	  	        objReturn = findFirstVesselByPid(fs, targetVesselPid);  	       
	      	}//end main:
	        return objReturn;
      }
       
     /**
   	 * Findet genau EIN Vessel über die Pid.
   	 * @throws ExceptionZZZ 
   	 */
       public static VesselMatch findFirstVesselByPid(File file, String targetVesselPid) throws ExceptionZZZ {
	       	VesselMatch objReturn = null;
	       	main:{       	
	   	        FlightstateMatch fs = SfsGameParser.parse(file);
	   	        objReturn = findFirstVesselByPid(fs, targetVesselPid);
	       	}//end main:
	        return objReturn;
       }
       
       /**
  	 * Findet genau EIN Vessel über die Pid
  	 * @throws ExceptionZZZ 
  	 */
      public static VesselMatch findSingleVesselByPid(File file, String targetVesselPid) throws ExceptionZZZ {
	      	VesselMatch objReturn = null;
	      	main:{
	      		if(StringZZZ.isEmpty(targetVesselPid)) {
	     			ExceptionZZZ ez = new ExceptionZZZ( "targetVesselPid-String", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;
	     		}
	      		
	  	        FlightstateMatch fs = SfsGameParser.parse(file);
	  	        
	  	        objReturn = findSingleVesselByPid(fs, targetVesselPid);
	      	}//end main:
	        return objReturn;
      }
      
      /**
 	 * Findet genau EIN Vessel über die Pid.
 	 * @throws ExceptionZZZ 
 	 */
     public static VesselMatch findFirstVesselByPid(FlightstateMatch fs, String targetVesselPid) throws ExceptionZZZ {
     	VesselMatch objReturn = null;
     	main:{         	     		
     		if(StringZZZ.isEmpty(targetVesselPid)) {
     			ExceptionZZZ ez = new ExceptionZZZ( "targetVesselPid-String", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
				throw ez;
     		}
     		
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
       public static VesselMatch findSingleVesselByPid(FlightstateMatch fs, String targetVesselPid) throws ExceptionZZZ {
	       	VesselMatch objReturn = null;
	       	main:{       
	       		if(fs==null) {
					ExceptionZZZ ez = new ExceptionZZZ( "FlightstateMatch-Object", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;		 
				}
	     		if(StringZZZ.isEmpty(targetVesselPid)) {
	     			ExceptionZZZ ez = new ExceptionZZZ( "targetVesselPid-String", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
					throw ez;
	     		}
	       		
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
   * Sucht alle Vessel innerhalb des FLIGHTSTATE.
   * @throws ExceptionZZZ 
   */
    public static List<VesselMatch> findAllVesselsByName(File saveFile, String targetVesselName) throws ExceptionZZZ {
    	if(StringZZZ.isEmpty(targetVesselName)) {
 			ExceptionZZZ ez = new ExceptionZZZ( "targetVesselName-String", iERROR_PARAMETER_MISSING, VesselFinder.class, ReflectCodeZZZ.getMethodCurrentName()); 
			throw ez;
 		}
    	
        FlightstateMatch flightState = SfsGameParser.parse(saveFile);
       
        List<VesselMatch> allVessels = SfsGameParser.parseVessels(flightState);

        List<VesselMatch> result = new ArrayList<VesselMatch>();

        for (VesselMatch v : allVessels) {

            if (targetVesselName.equals(v.getVesselName())) {
                result.add(v);
            }
        }

        return result;
    }      
}
