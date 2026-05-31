package use.tool.ksp.util;

import java.io.File;
import java.util.List;

public class AbstractSfsParser implements ISfsParser{
	protected File objFile = null;
	
	
	public AbstractSfsParser() {		
	}
	
	public AbstractSfsParser(File objFile) {	
		this.setFile(objFile);
	}
	
	
	//### Getter/Setter
	@Override
	public void setFile(File objFile) throws IllegalArgumentException {
		if(objFile!=null) {
			if(!objFile.exists()) throw new IllegalArgumentException("No file found at '" + objFile.getAbsolutePath() + "'" );
		}
		this.objFile = objFile;
	}
	
	@Override
	public File getFile() {
		return this.objFile;
	}
	
	
	 /**
     * Echter Vessel-Start:
     *
     * VESSEL
     * {
     *     pid =
     */
    protected static boolean isRealVesselStart(
            List<String> allLines,
            int index) {

        if (index + 2 >= allLines.size()) {

            return false;
        }

        String line0 =
                allLines.get(index).trim();

        String line1 =
                allLines.get(index + 1).trim();

        String line2 =
                allLines.get(index + 2).trim();

        if (!"VESSEL".equals(line0)) {

            return false;
        }

        if (!"{".equals(line1)) {

            return false;
        }

        if (!line2.startsWith("pid =")) {

            return false;
        }

        return true;
    }
    
    /** Brace Engine (wichtig!)
     * @param brace
     * @param line
     * @return
     */
    protected static int updateBrace(int brace, String line) {

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '{') brace++;
            if (c == '}') brace--;
        }

        return brace;
    }
}
