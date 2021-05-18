package com.serviceManagementAPI.Database.CenterSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface CenterScheduleRepository extends JpaRepository<CenterSchedule, Integer> {


    @Query("FROM CenterSchedule WHERE technicianId = ?1 AND centerId = ?2 AND date = ?3")
    List<CenterSchedule> findByTechnicianIdAndCenterId(int technicianId, int centerId, LocalDate date);

    @Query("FROM CenterSchedule WHERE technicianId = ?1 AND centerId = ?2 AND date = ?3")
    CenterSchedule findByTask(int technicianId, int centerId, LocalDate date);

    @Query("FROM CenterSchedule WHERE centerId = ?1 AND date = ?2 AND status = true")
    List<CenterSchedule> findAllByCenterIdAndDate(int centerId, LocalDate date);

    @Query("FROM CenterSchedule WHERE centerId = ?1 AND date = ?2 AND startTime = ?3 AND status = true")
    List<CenterSchedule> findAllByCenterIdAndDateAndTime(int centerId, LocalDate date, Time startTime);

    @Query("FROM CenterSchedule WHERE centerId = ?1 AND startTime = ?2 AND status = true")
    List<CenterSchedule> findAllByCenterIdAndTime(int centerId, Time startTime);

    @Query("FROM CenterSchedule WHERE centerId = ?1 AND status = true")
    List<CenterSchedule> findAllByCenter(int centerId);

    @Query("FROM CenterSchedule WHERE date = ?1 AND startTime = ?2 AND status = true")
    List<CenterSchedule> findAllByDateAndTime(LocalDate date, Time startTime);

    @Query("FROM CenterSchedule WHERE date = ?1 AND status = true")
    List<CenterSchedule> findAllByDate(LocalDate date);

    @Query("FROM CenterSchedule WHERE startTime = ?1 AND status = true")
    List<CenterSchedule> findAllByTime(Time startTime);
}
