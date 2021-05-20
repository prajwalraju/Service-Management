package com.serviceManagementAPI.Database.ServiceCenter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "service_center")
public class ServiceCenterEntity {
    @Id
    private int centerId;
    private String location;
    private Time startTime;
    private Time stopTime;
    private boolean status;

    public ServiceCenterEntity() {
    }

    public ServiceCenterEntity(int centerId,
                               String location,
                               Time startTime,
                               Time stopTime,
                               boolean status) {
        this.centerId = centerId;
        this.location = location;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.status = status;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getStopTime() {
        return stopTime;
    }

    public void setStopTime(Time stopTime) {
        this.stopTime = stopTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
