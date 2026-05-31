package use.tool.ksp;

import use.tool.ksp.util.SfsGameParser;

public abstract class AbstractVesselTool implements IVesselTool{

    protected SfsGameParser parser=null;

    protected AbstractVesselTool() {    	
    }
    
    protected AbstractVesselTool(SfsGameParser parser) {
        this.parser = parser;
    }

    //### GETTER / SETTER
    public void setParser(SfsGameParser objParser) {
    	this.parser = objParser;
    }
    public SfsGameParser getParser() {
    	return this.parser;
    }
}
