package com.serviceManagementAPI.Database.TechnicianList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TechnicianList {
    @Id
    private int technicianId;

    private int centerId;
    private boolean status;

    public TechnicianList() {
    }

    public TechnicianList(int technicianId, int centerId, boolean status) {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
