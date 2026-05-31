package use.tool.ksp.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public final class VesselValidator {
	private static final Pattern VALID_KEY_VALUE =
	        Pattern.compile("^\\s*[A-Za-z0-9_\\-.]+\\s*=.*$");

	private static final Pattern VALID_NODE =
	        Pattern.compile("^\\s*[A-Z0-9_]+\\s*$");
	
	
	
    private VesselValidator() {
    }

    public static void validateVessel(File fileVessel) throws IOException {
    	VesselValidator.validateVessel(fileVessel.toPath());
    }
    
    public static void validateVessel(Path vesselFile) throws IOException {

        List<String> lines = Files.readAllLines(vesselFile, StandardCharsets.UTF_8);

        validateNoInvalidLines(lines);

        validateCurlyBrackets(lines);

        validateStartsWithVessel(lines);
    }

	private static void validateStartsWithVessel(List<String> lines) {
		// TODO Auto-generated method stub
		
	}

	private static void validateCurlyBrackets(List<String> lines) {
		// TODO Auto-generated method stub
		
	}

	private static void validateNoInvalidLines(List<String> lines) {
		// TODO Auto-generated method stub
		
	}

}
