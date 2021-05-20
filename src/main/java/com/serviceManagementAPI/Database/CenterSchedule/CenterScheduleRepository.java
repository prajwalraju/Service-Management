package com.serviceManagementAPI.Database.CenterSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CenterScheduleRepository extends JpaRepository<CenterScheduleEntity, Integer> {

    @Query("FROM CenterScheduleEntity WHERE technicianId = ?1 AND centerId = ?2 AND date = ?3")
    Optional<CenterScheduleEntity> findByTask(int technicianId, int centerId, LocalDate date);

    @Query("FROM CenterScheduleEntity WHERE centerId = ?1 AND date = ?2 AND status = true")
    List<CenterScheduleEntity> findAllByCenterIdAndDate(int centerId, LocalDate date);

    @Query("FROM CenterScheduleEntity WHERE centerId = ?1 AND date = ?2 AND startTime = ?3 AND status = true")
    List<CenterScheduleEntity> findAllByCenterIdAndDateAndTime(int centerId, LocalDate date, Time startTime);

    @Query("FROM CenterScheduleEntity WHERE centerId = ?1 AND startTime = ?2 AND status = true")
    List<CenterScheduleEntity> findAllByCenterIdAndTime(int centerId, Time startTime);

    @Query("FROM CenterScheduleEntity WHERE centerId = ?1 AND status = true")
    List<CenterScheduleEntity> findAllByCenter(int centerId);

    @Query("FROM CenterScheduleEntity WHERE date = ?1 AND startTime = ?2 AND status = true")
    List<CenterScheduleEntity> findAllByDateAndTime(LocalDate date, Time startTime);

    @Query("FROM CenterScheduleEntity WHERE date = ?1 AND status = true")
    List<CenterScheduleEntity> findAllByDate(LocalDate date);

    @Query("FROM CenterScheduleEntity WHERE startTime = ?1 AND status = true")
    List<CenterScheduleEntity> findAllByTime(Time startTime);
}
