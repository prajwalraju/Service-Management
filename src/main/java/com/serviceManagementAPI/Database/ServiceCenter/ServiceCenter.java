package com.serviceManagementAPI.Database.ServiceCenter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

public class ServiceCenter {
    @NotNull
    private int centerId;
    @NotNull
    @Size(min = 2, message = "Location should be more than 2 letters")
    private String location;
    @NotNull
    private Time startTime;
    @NotNull
    private Time stopTime;
    @NotNull
    private boolean status;

    public ServiceCenter(int centerId,
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
