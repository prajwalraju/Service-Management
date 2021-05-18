package com.serviceManagementAPI.Database.ServiceCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;

@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Integer> {

    ArrayList<ServiceCenter> findAllByStatus(boolean b);

    @Query("FROM ServiceCenter WHERE centerId = ?1 AND status = true")
    ArrayList<ServiceCenter> findByCenterAndStatus(int centerId);

    @Query("FROM ServiceCenter WHERE centerId = ?1 AND startTime < ?2")
    ArrayList<ServiceCenter> ValidStartTime(int centerId, Time startTime);

    @Query("FROM ServiceCenter WHERE centerId = ?1 AND stopTime > ?2")
    ArrayList<ServiceCenter> ValidStopTime(int centerId, Time endTime);

    @Query(value = "SELECT * FROM center_schedule WHERE center_id = ?1 AND start_time > (SELECT subtime(?2, '0:30:0'));", nativeQuery = true)
    ArrayList<ServiceCenter> ValidCustomerStopTime(int centerId, Time startTime);
}
