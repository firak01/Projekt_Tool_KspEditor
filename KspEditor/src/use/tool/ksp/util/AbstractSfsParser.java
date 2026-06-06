package use.tool.ksp.util;

import java.io.File;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.IConstantZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import basic.zBasic.util.file.FileTextUtilZZZ;

public class AbstractSfsParser implements IConstantZZZ, ISfsParser{
	protected File objFile = null;
	
	
	public AbstractSfsParser() {		
	}
	
	public AbstractSfsParser(File objFile) throws ExceptionZZZ {	
		this.setFile(objFile);
	}
	
	
	//### Getter/Setter
	@Override
	public void setFile(File objFile) throws ExceptionZZZ {
		if(objFile!=null) {
	    	if(!FileEasyZZZ.isFileExisting(objFile)) {
				ExceptionZZZ ez = new ExceptionZZZ( "File-Object does not exist or is an directory: '"+objFile.getAbsolutePath() + "'", iERROR_PROPERTY_MISSING, FileTextUtilZZZ.class, ReflectCodeZZZ.getMethodCurrentName()); 
				throw ez;		 
			}
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
    public static boolean isRealVesselStart(
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
    
    
    /**Weil PART Zeilen ggfs. auch innerhalb von anderen Modulen (z.B. KIS-Mod Containern) liegen können, 
     * wird diese Methode benötigt für die Parser.
     * @param allLines
     * @param index
     * @return
     */
    public static boolean isRealVesselPartStart(
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
        
        
        if (!"PART".equals(line0)) {

            return false;
        }else {
//        	System.out.println("debug break 3");
        }

        if (!"{".equals(line1)) {

            return false;
        }

        if (!line2.startsWith("name =")) {

            return false;
        }

        /*
         * Zusätzliche Prüfung:
         * Ein echter PART liegt direkt innerhalb des VESSEL.
         */
        int nestingLevel =
                determineNestingLevel(allLines, index);

        //Wir übergeben auch reine VESSEL Dateien, dann ist VESSEL oberste Ebene und nicht GAME. Somit verschiebt sich die Ebene.
        return nestingLevel <= 2; //return nestingLevel == 2;
    }
    
    /**Dabei gilt:

Position	Level
innerhalb VESSEL	1
innerhalb PART		2
innerhalb MODULE	3
innerhalb ITEM		4
KIS-PART			5

Ein echter PART-Start wird also genau bei level == 1 gefunden, bevor seine öffnende { gelesen wurde. Je nach Implementierung kann auch level == 2 korrekt sein; das muss zum Zählzeitpunkt passen.
     * @param allLines
     * @param endExclusive
     * @return
     */
    public static int determineNestingLevel(
            List<String> allLines,
            int endExclusive) {

        int level = 0;

        for (int i = 0; i < endExclusive; i++) {

            String line =
                    allLines.get(i).trim();

            if ("{".equals(line)) {

                level++;
            }
            else if ("}".equals(line)) {

                level--;
            }
        }

        return level;
    }
}
