package com.serviceManagementAPI.Database.CenterSchedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
public class CenterSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int technicianId;
    //    @NotBlank(message = "Center ID is mandatory")
    private int centerId;
    //    @NotBlank(message = "Date is mandatory")
    private Date date;
    private Time startTime;
    private Time endTime;
    private boolean status;

    public CenterSchedule() {
    }

    public CenterSchedule(int id,
                          int technicianId,
                          int centerId,
                          Date date,
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
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

    public boolean isStartTime() {
        return startTime != null;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }


}
