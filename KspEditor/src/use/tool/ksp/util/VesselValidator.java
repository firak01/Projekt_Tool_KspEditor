package use.tool.ksp.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.IConstantZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.file.FileEasyZZZ;

public final class VesselValidator implements IConstantZZZ{
	private static final Pattern VALID_KEY_VALUE =
	        Pattern.compile("^\\s*[A-Za-z0-9_\\-.]+\\s*=.*$");

	private static final Pattern VALID_NODE =
	        Pattern.compile("^\\s*[A-Z0-9_]+\\s*$");
	
	
	
    private VesselValidator() {
    }

    public static void validateVesselFile(File objFile) throws ExceptionZZZ {
    	
    	if(!FileEasyZZZ.isFileExisting(objFile)) {
			ExceptionZZZ ez = new ExceptionZZZ( "File-Object does not exist or is an directory: '"+objFile.getAbsolutePath() + "'", iERROR_PROPERTY_MISSING, VesselValidator.class, ReflectCodeZZZ.getMethodCurrentName()); 
			throw ez;		 
		}
    	
    	VesselValidator.validateVesselFile(objFile.toPath());
    }
    
   
    
    public static void validateVesselFile(Path objPath) throws ExceptionZZZ {
    	
    	try {
    	
	        List<String> lines = Files.readAllLines(objPath, StandardCharsets.UTF_8);
	
	        validateNoInvalidLines(lines);
	
	        validateCurlyBrackets(lines);
	
	        validateStartsWithVessel(lines);
	        
    	}catch(IOException ioe) {
    		ExceptionZZZ ez = new ExceptionZZZ(ioe);
    		throw ez;
    	}
    }

	private static void validateStartsWithVessel(List<String> lines) throws ExceptionZZZ {
		// TODO Auto-generated method stub
		
	}

	private static void validateCurlyBrackets(List<String> lines) throws ExceptionZZZ {
		// TODO Auto-generated method stub
		
	}

	private static void validateNoInvalidLines(List<String> lines)  throws ExceptionZZZ {
		// TODO Auto-generated method stub
		
	}

}
