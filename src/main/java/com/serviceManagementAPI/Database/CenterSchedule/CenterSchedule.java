package com.serviceManagementAPI.Database.CenterSchedule;

import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDate;


@SuppressWarnings("unused")
public class CenterSchedule {

    @Valid
    private int id;
    @Valid
    private int technicianId;
    @Valid
    private int centerId;
    @Valid
    private LocalDate date;
    @Valid
    private Time startTime;
    @Valid
    private Time endTime;
    @Valid
    private boolean status;

    public CenterSchedule(int id,
                          int technicianId,
                          int centerId,
                          LocalDate date,
                          Time startTime,
                          Time endTime,
                          boolean status) {
        this.id = id;
        this.technicianId = technicianId;
        this.centerId = centerId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
