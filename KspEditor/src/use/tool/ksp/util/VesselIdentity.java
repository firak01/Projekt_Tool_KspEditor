package use.tool.ksp.util;

public class VesselIdentity {

    private String sPersistenceId=null;
    private String sPid=null;
    
    
    public VesselIdentity() {
    	
    }
    
    public VesselIdentity(String sPersistenceId, String sPid) {
        this.sPersistenceId = sPersistenceId;
        this.sPid = sPid;
    }

    public void setPersistenceId(String sPersitanceId) {
        this.sPersistenceId = sPersitanceId;
    }
    public String getPersistenceId() {
        return this.sPersistenceId;
    }
   
    public void setPID(String sPID) {
    	this.sPid = sPID;
    }
    public String getPId() {
        return this.sPid;
    }

}
