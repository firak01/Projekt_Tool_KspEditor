package use.tool.ksp;

import use.tool.ksp.util.ISfsParser;
import use.tool.ksp.util.SfsGameParser;

public interface IParserUsingTool {
	public void setParser(ISfsParser objParser);
	public ISfsParser getParser();
}
