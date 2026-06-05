package use.tool.ksp;

import java.io.File;

import basic.zBasic.ExceptionZZZ;
import use.tool.ksp.util.SfsGameParser;

public class GameEditor extends AbstractParserUsingTool{
	public GameEditor() {
		super();
	}
    public GameEditor(SfsGameParser parser) {
        super(parser);
    }
    
    public boolean replaceVessel(File fileVessel) throws ExceptionZZZ{
    	boolean bReturn = false;
    	main:{

    		  SfsGameParser objGameParser = (SfsGameParser) this.getParser();
    		  
			  VesselReplacer objReplacerVessel = new VesselReplacer(objGameParser);
			  bReturn = objReplacerVessel.replaceVessel(fileVessel);
			  
    	}//end main:
    	return bReturn;
    }

}
