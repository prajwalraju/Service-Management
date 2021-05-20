package com.serviceManagementAPI.Database.TechnicianList;

import javax.validation.constraints.NotNull;

public class TechnicianList {

    @NotNull
    private int technicianId;
    @NotNull
    private int centerId;
    @NotNull
    private boolean status;

    public TechnicianList(int technicianId,
                          int centerId,
                          boolean status) {
        this.technicianId = technicianId;
        this.centerId = centerId;
        this.status = status;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
