package com.serviceManagementAPI.Database.ServiceCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;

@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenterEntity, Integer> {

    ArrayList<ServiceCenterEntity> findAllByStatus(boolean b);

    @Query("FROM ServiceCenterEntity WHERE centerId = ?1 AND status = true")
    ArrayList<ServiceCenterEntity> findByCenterAndStatus(int centerId);

    @Query("FROM ServiceCenterEntity WHERE centerId = ?1 AND startTime < ?2")
    ArrayList<ServiceCenterEntity> ValidStartTime(int centerId, Time startTime);

    @Query("FROM ServiceCenterEntity WHERE centerId = ?1 AND stopTime > ?2")
    ArrayList<ServiceCenterEntity> ValidStopTime(int centerId, Time endTime);

    @Query(value = "SELECT * FROM center_schedule WHERE center_id = ?1 AND start_time > (SELECT subtime(?2, '0:30:0'));", nativeQuery = true)
    ArrayList<ServiceCenterEntity> ValidCustomerStopTime(int centerId, Time startTime);
}
