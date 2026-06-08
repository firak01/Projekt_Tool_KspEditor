package use.tool.ksp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.file.FileEasyZZZ;
import use.tool.ksp.util.AbstractSfsParser;

/**
 * KSP 1.5.1 .sfs Parent-Aktualisierer
 *
 * Java 1.7 kompatibel
 */
public class StructureEditor {

   
    /**
     * Aktualisiert innerhalb aller PART{...}-Blöcke
     * die parent = ... Werte.
     * @param listaLine
     * @param iParentFirst Wert für den ersten PART-Block
     * @param iParentStartOthers Startwert für weitere PART-Blöcke
     * @return
     * @throws Exception
     */
    public static List<String> updateParentValues(
    		List<String> listaLine,
            int iParentFirst,
            int iParentStartOthers            
    ) throws Exception {

        List<String> listasReturn = new ArrayList<String>();

        boolean bInsidePart = false;
        int iPartCount = 0;
        int iCurrentParent = iParentStartOthers;

        Pattern objPatternParent =
                Pattern.compile("^\\s*parent\\s*=\\s*-?\\d+\\s*$");

        int iLine = 0;
        for (String sLine : listaLine) {

            String sLineNew = sLine;

            // Beginn eines PART-Blocks
            // so einfach ist nicht ausreichen. if (sLine.trim().equals("PART")) {
            boolean bRealVesselPart = AbstractSfsParser.isRealVesselPartStart(listaLine, iLine);
            if(bRealVesselPart) {
                bInsidePart = true;
            }

            // parent = ... ersetzen
            if (bInsidePart) {

                Matcher objMatcher = objPatternParent.matcher(sLine);

                if (objMatcher.matches()) {

                    int iNewValue;

                    // Erster PART
                    if (iPartCount == 0) {
                        iNewValue = iParentFirst;
                    } else {
                        iNewValue = iCurrentParent;
                        iCurrentParent++;
                    }

                    sLineNew = replaceParentLine(sLine, iNewValue);
                    iPartCount++;
                }                
                
            }

            listasReturn.add(sLineNew);

            // Ende eines PART-Blocks
            // Achtung:
            // sehr einfach gehalten.
            // Funktioniert für normale PART-Strukturen.
            if (bInsidePart && sLine.trim().equals("}")) {
                bInsidePart = false;
            }
            
            iLine++;
        }

      return listasReturn; 
    }
    
    /**
     * Aktualisiert innerhalb aller PART{...}-Blöcke
     * die attN = ... Werte.
     * @param listaLine
     * @param iParent Wert des Parent, enspricht dem attN Wert
     * @param sAttnNodeDefault
     * @return
     * @throws Exception
     * 
      //TODOGOON20260607;//Es muss noch der attnNode überarbeitet werden.                
                //Zielwerte aus dem schon passend überarbeiten Beispiel:
                //parent = 64
                //attN = top, 256
        		//attN = bottom, 64
                
                //attN = top,    <im ersten PART der Strukur kommt die Anzahl der Teile des VESSEL rein + 1, also der dynamische Indexwert des folgenden PART>
                //attN = bottom, <im ersten PART der Strukur kommt der dynamische Indexwert des Parent rein>
                                               
                //IDEE: Errechne aus dem Ausgangsindex den Nun zu verwendenden Index (quasi eine Art offset) 
                //IDEE: übergib irgendwie den Ausgangsindex als Wert aus dem ersten PART, attn = top ... also hier 455 
                //parent = 453
                //attN = top, 455
        		//attN = bottom, 453
        			
                
                //Das zweite PART der Struktur hat
                //Zielwerte aus dem schon passend überarbeiten Beispiel:
                //parent = 255
                //attN = bottom, -1
                //attN = top, 255
              
                //attN = top,    <im zweiten PART der Strukur kommt der dynamische Indexwert des Parent rein>
                //attN = bottom, <im zweiten PART der Strukur kommt -1 rein, also keine Verbindung>
              
                
                //Ausgangswerte zur IDEE zur offset Berechnung, 
                //das zweite Teil hatte also den dynamischen Index 455                
                //das erste Teil hatte also den dynamsichen Index 454
                //parent = 454
                //attN = bottom, -1
        		//attN = top, 454		
               
               
                //BEACHTE
                //weitere PARTs
                //und auch srfN = srfAttach, 455
                
     */
    public static List<String> updateAttnValues(
            List<String> listaLine,
            int iParentFirst,
            int iParentStartOthers,
            String sAttnNodeDefault           
    ) throws Exception {
    	
        List<String> listasReturn = new ArrayList<String>();

        boolean bInsidePart = false;
        int iPartCount = 0;
                
//        Pattern objPatternParent =
//                Pattern.compile("^\\s*parent\\s*=\\s*-?\\d+\\s*$");
        
        Pattern objPatternAttn =
                Pattern.compile(
                        "^\\s*attN\\s*=\\s*"
                        + Pattern.quote(sAttnNodeDefault)
                        + "\\s*,\\s*-?\\d+\\s*$"
                );

        int iLine = 0;
        int iPartIndex = -1;
        for (String sLine : listaLine) {

            String sLineNew = sLine;

            // Beginn eines PART-Blocks
            // so einfach ist nicht ausreichen. if (sLine.trim().equals("PART")) {
            boolean bRealVesselPart = AbstractSfsParser.isRealVesselPartStart(listaLine, iLine);           

            if(bRealVesselPart) {
                bInsidePart = true;
                iPartIndex++;
            }

            // attN = ... ersetzen
            if (bInsidePart) {

                Matcher objMatcher = objPatternAttn.matcher(sLine);

                if (objMatcher.matches()) {

                    int iNewValue;
                    if(iPartIndex == 0) {
                    	   // erstes PART
                    	
                    	sLineNew = replaceAttnLine(sLine, "top", iParentFirst);
                    }
                    else if(iPartIndex == 1) {
                    	   // zweites PART und folgende
                    	iNewValue = iParentStartOthers + iPartIndex;
                    	sLineNew = replaceAttnLine(sLine, sAttnNodeDefault, iNewValue);
                    }

                                                          
                }                               
                iPartCount++;
            }

            listasReturn.add(sLineNew);

            // Ende eines PART-Blocks
            // Achtung:
            // sehr einfach gehalten.
            // Funktioniert für normale PART-Strukturen.
            if (bInsidePart && sLine.trim().equals("}")) {
                bInsidePart = false;
            }
            
            iLine++;
        }

        return listasReturn;
    }

    /**
     * Ersetzt den parent-Wert einer Zeile.
     *
     * Beispiel:
     * parent = 5
     * ->
     * parent = 999
     */
    public static String replaceParentLine(
            String sLine,
            int iNewParentValue
    ) {

        return sLine.replaceAll(
                "(^\\s*parent\\s*=\\s*)-?\\d+(\\s*$)",
                "$1" + iNewParentValue + "$2"
        );
    }
    
//    /**
//     * Ersetzt den attN-Wert einer Zeile.
//     *
//     * Beispiel:
//     * parent = 5
//     * ->
//     * parent = 999
//     */
//    public static String replaceAttnLine(
//            String sLine,
//            String sAttnNode,
//            int iNewParentValue
//    ) {
//
//        //Beispiel für eine attN - Zeile:
//    	//attN = bottom, 64
//    	return "attN = bottom, 64"; //DUMMY AUSGABE
//    }
    
    
    /**
     * Ersetzt den Wert einer attN-Zeile für den gewünschten Node.
     *
     * Beispiel:
     * attN = bottom, 64
     * ->
     * attN = bottom, 453
     */
    public static String replaceAttnLine(
            String sLine,
            String sAttnNode,
            int iNewValue
    ) {

        Pattern objPattern = Pattern.compile(
                "^(\\s*attN\\s*=\\s*)(" +
                Pattern.quote(sAttnNode) +
                ")(\\s*,\\s*)-?\\d+(\\s*)$"
        );

        Matcher objMatcher = objPattern.matcher(sLine);

        if(objMatcher.matches()) {

            return objMatcher.group(1)
                    + objMatcher.group(2)
                    + objMatcher.group(3)
                    + iNewValue
                    + objMatcher.group(4);
        }

        return sLine;
    }
    
    /**
     * Aktualisiert den numerischen Wert einer attN-Zeile
     * für einen bestimmten Node.
     */
    public static String updateAttnValue(
            String sLine,
            String sAttnNode,
            int iNewValue
    ) {
        return replaceAttnLine(
                sLine,
                sAttnNode,
                iNewValue
        );
    }

    /**
     * Erzeugt Ausgabedatei mit Suffix.
     *
     * Beispiel:
     * persistent.sfs
     * ->
     * persistent_STEP01.sfs
     * @throws ExceptionZZZ 
     */
    public static File createOutputFile(
            File objFileIn,
            String sSuffix
    ) throws ExceptionZZZ {

        String sName = objFileIn.getName();

        int iPos = sName.lastIndexOf('.');

        String sNameOnly;
        String sExtension;

        if (iPos >= 0) {
            sNameOnly = sName.substring(0, iPos);
            sExtension = sName.substring(iPos);
        } else {
            sNameOnly = sName;
            sExtension = "";
        }

        String sNewName =
                sNameOnly + sSuffix + sExtension;

        File objFileDirectory = objFileIn.getParentFile();
        String sDirectoryOutput = objFileDirectory.getParentFile().getAbsolutePath() + "\\output";
        File objFileDirectoryOutput = new File(sDirectoryOutput); 
        boolean bSuccess = FileEasyZZZ.makeDirectory(objFileDirectoryOutput);
        return new File(
                objFileDirectoryOutput,
                sNewName
        );
    }

    /**
     * Schreibt Zeilen in Datei.
     */
    public static void writeLines(
            File objFileOut,
            List<String> listaLine
    ) throws IOException {

        BufferedWriter objWriter = Files.newBufferedWriter(
                objFileOut.toPath(),
                Charset.forName("UTF-8")
        );

        try {

            for (String sLine : listaLine) {
                objWriter.write(sLine);
                objWriter.newLine();
            }

        } finally {
            objWriter.close();
        }
    }
}
