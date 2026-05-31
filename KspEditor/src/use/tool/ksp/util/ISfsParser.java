package use.tool.ksp.util;

import java.io.File;

/**Grundidee: State Machine statt String-Suche

Wir ersetzen:

fragile startsWith
fragile Regex
fragile line checks

durch:

👉 Zustandsmaschine + Brace Tracking
 * @author Fritz Lindhauer
 *
 */
public interface ISfsParser {
	enum ParseState {
	    OUTSIDE,
	    IN_FLIGHTSTATE,
	    IN_VESSEL,
	    IN_PART,
	    IN_MODULE,
	    IN_ACTIONGROUPS
	}
	
	public File getFile();
	public void setFile(File objFile) throws IllegalArgumentException;
}
