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
    
    /**Methode reicht nur die Daten des Vessel 
     * und die für das "Anhängen" der Struktur notwendigen Daten 
     * an den "StructurAdder" weiter.
     * @param fileStructure
     * @return
     * @throws ExceptionZZZ
     */
    public boolean addStructure(File fileStructure) throws ExceptionZZZ{
    	boolean bReturn = false;
    	main:{

    		  SfsVesselParser objVesselParser = (SfsVesselParser) this.getParser();
    		  File fileVessel = objVesselParser.getFile();
    		  
    		  TODOGOON20260616;
    		  StructureAdder objStructureAdder = new StructureAdder(objVesselParser);
    		  boolean bSuccess = objStructureAdder.addStructure(fileVessel, fileStructure, iPegElementIndex, objEnumPegPartNode);
 			 
    		 
			  
    	}//end main:
    	return bReturn;
    }

}
