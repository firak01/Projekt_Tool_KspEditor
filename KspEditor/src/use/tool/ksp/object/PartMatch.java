package use.tool.ksp.object;

import java.util.List;

public class PartMatch {

    private final int startLine;
    private final int endLine;

    private final List<String> lines;

    public PartMatch(int startLine, int endLine, List<String> lines) {
        this.startLine = startLine;
        this.endLine = endLine;
        this.lines = lines;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public List<String> getLines() {
        return lines;
    }

    public String getPartName() {

        for (String line : lines) {

            String trimmed = line.trim();

            if (trimmed.startsWith("name =")) {
                return trimmed.substring("name =".length()).trim();
            }
        }

        return null;
    }

    @Override
    public String toString() {

        return "PartMatch [startLine="
                + startLine
                + ", endLine="
                + endLine
                + ", name="
                + getPartName()
                + "]";
    }
}
