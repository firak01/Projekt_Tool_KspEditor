package use.tool.ksp;

import basic.zBasic.IConstantZZZ;
import use.tool.ksp.util.ISfsParser;
import use.tool.ksp.util.SfsGameParser;

public abstract class AbstractParserUsingTool implements IConstantZZZ, IParserUsingTool{

    protected ISfsParser parser=null;

    protected AbstractParserUsingTool() {    	
    }
    
    protected AbstractParserUsingTool(ISfsParser parser) {
        this.parser = parser;
    }

    //### GETTER / SETTER
    public void setParser(ISfsParser objParser) {
    	this.parser = objParser;
    }
    public ISfsParser getParser() {
    	return this.parser;
    }
}
