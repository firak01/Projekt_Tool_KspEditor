package use.tool.ksp.util;

import basic.zBasic.util.datatype.enums.EnumUtilZZZ;

public class InputValidationUtil {
	public static ISfsStructureParser.PegPartNode parsePegPartNode(String value) {
//	    for(ISfsStructureParser.PegPartNode node :
//	            ISfsStructureParser.PegPartNode.values()) {
//
//	        if(node.name().equalsIgnoreCase(value)) {
//	            return node;
//	        }
//	    }
//
//	    throw new IllegalArgumentException(
//	            "Ungültiger PegPartNode: '" + value + "'");
		
		//Verkürzt durch eine utility Klasse.
		ISfsStructureParser.PegPartNode objEnumNode = EnumUtilZZZ.parseEnumIgnoreCase(ISfsStructureParser.PegPartNode.class, value);
		return objEnumNode;
	}	
}
