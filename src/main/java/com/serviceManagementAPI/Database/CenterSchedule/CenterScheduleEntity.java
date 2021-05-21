package com.serviceManagementAPI.Database.CenterSchedule;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "center_schedule")
@SuppressWarnings("unused")
public class CenterScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int technicianId;
    private int centerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private boolean status;

    public CenterScheduleEntity() {
    }

    public CenterScheduleEntity(int id,
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

    public boolean isCenter() {
        return !String.valueOf(centerId).equals("0");
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

    public boolean isDate() {
        return date != null;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
