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

    public void setPersistentId(String sPersitanceId) {
        this.sPersistenceId = sPersitanceId;
    }
    public String getPersistenId() {
        return this.sPersistenceId;
    }
   
    public void setPid(String sPid) {
    	this.sPid = sPid;
    }
    public String getPid() {
        return this.sPid;
    }

}
