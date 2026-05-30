package use.tool.ksp.util;

public class VesselIdentity {

    private final String persistenceId;

    public VesselIdentity(String persistenceId) {
        this.persistenceId = persistenceId;
    }

    public String getPersistenceId() {
        return persistenceId;
    }

}
