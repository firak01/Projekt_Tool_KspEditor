package use.tool.ksp;

import use.tool.ksp.util.SfsParser;

public abstract class AbstractVesselTool {

    protected final SfsParser parser;

    protected AbstractVesselTool(SfsParser parser) {
        this.parser = parser;
    }

}
