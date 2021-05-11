package com.serviceManagementAPI.Database.CenterSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CenterScheduleRepository extends JpaRepository<CenterSchedule, Integer> {

    @Query("FROM CenterSchedule WHERE centerId = ?1 AND date = ?2")
    List<CenterSchedule> findAllByCenterIdAndDate(int centerId, Date date);

}
