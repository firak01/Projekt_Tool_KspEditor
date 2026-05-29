package use.tool.ksp.object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightStateMatch {

    private int startLine;

    private int endLine;

    private List<String> lines =
            new ArrayList<String>();

    public FlightStateMatch() {

    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Debug-Ausgabe des kompletten FLIGHTSTATE Blocks.
     */
    public void debugWriteToFile(File outputDirectory)
            throws IOException {

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        File outFile =
                new File(outputDirectory,
                        "FLIGHTSTATE_DEBUG.sfs");

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(outFile));

        try {

            for (String line : lines) {

                writer.write(line);
                writer.newLine();
            }

        } finally {

            writer.close();
        }

        System.out.println(
                "DEBUG FlightState geschrieben: "
                        + outFile.getAbsolutePath());
    }
}


