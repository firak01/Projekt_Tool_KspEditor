package use.tool.ksp.util;

public interface ISfsStructureParser {
	//Innerhalb der Part Struktur vorhandene Verbindungsstellen
	
	//Das ist vom Typ her das gesuchte Anbindeelement hat als ein Würfel 6 Anbindestellen
	//name = structuralMiniNode
	//
	//attN = bottom, -1
	//Dies ist also unverbunden, im Gegensatz zur Zeile
	//attN = front, 63
	//Dann gibt es noch ungenutzte Verbindungstellen
	//attN = right, -1
	//attN = left, -1
	//attN = back, -1
	//attN = top, -1
	enum PegPartNode {
		bottom,
		front,
		right,
		left,
		back,
		top
	}
}
