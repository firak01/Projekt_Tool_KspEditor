package use.tool.ksp.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.util.file.FileTextUtilZZZ;
import use.tool.ksp.object.PartMatch;
import use.tool.ksp.object.VesselMatch;

public class SfsStructureParser  extends AbstractSfsParser{

	public SfsStructureParser() {
		super();
	}
	
	public SfsStructureParser(File objFile) throws ExceptionZZZ {
		super(objFile);
	}
	
	
	public List<PartMatch> parse() throws ExceptionZZZ {
		File objFile = this.getFile();
		return SfsStructureParser.parse(objFile);		
	}
	
	
    public static List<PartMatch> parse(File fileStructure) throws ExceptionZZZ {

//        List<String> lines = Files.readAllLines(
//                file.toPath(),
//                Charset.forName("UTF-8"));
        List<String> allLines = FileTextUtilZZZ.readFileToList(fileStructure);         
        return parse(allLines);
    }

    public static List<PartMatch> parse(List<String> lines) {

        List<PartMatch> result = new ArrayList<PartMatch>();

        boolean insidePart = false;

        int partStartLine = -1;
        int braceLevel = 0;

        List<String> currentPartLines = null;

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i);
            String trimmed = line.trim();

            /*
             * Kommentare ignorieren
             */
            if (trimmed.startsWith("#")) {
                continue;
            }

            /*
             * Beginn eines PART-Blocks
             */
            if (!insidePart && "PART".equals(trimmed)) {

                insidePart = true;
                partStartLine = i;

                currentPartLines = new ArrayList<String>();
                currentPartLines.add(line);

                continue;
            }

            if (!insidePart) {
                continue;
            }

            currentPartLines.add(line);

            if (trimmed.contains("{")) {
                braceLevel++;
            }

            if (trimmed.contains("}")) {
                braceLevel--;

                /*
                 * PART abgeschlossen
                 */
                if (braceLevel == 0) {

                    PartMatch match =
                            new PartMatch(
                                    partStartLine,
                                    i,
                                    currentPartLines);

                    result.add(match);

                    insidePart = false;
                    currentPartLines = null;
                }
            }
        }

        return result;
    }
}
