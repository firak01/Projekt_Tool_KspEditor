package use.tool.ksp;

import java.io.File;

import basic.zBasic.ExceptionZZZ;
import use.tool.ksp.util.SfsGameParser;
import use.tool.ksp.util.SfsVesselParser;

public class VesselEditor extends AbstractParserUsingTool{
	public VesselEditor() {
		super();
	}
    public VesselEditor(SfsVesselParser parser) {
        super(parser);
    }
    
    public boolean addStructure(File fileStructure) throws ExceptionZZZ{
    	boolean bReturn = false;
    	main:{

    		  SfsVesselParser objVesselParser = (SfsVesselParser) this.getParser();
    		  
    		  StructureAdder objStructureAdder = new StructureAdder(objVesselParser);
    		 
			  
    	}//end main:
    	return bReturn;
    }

}
