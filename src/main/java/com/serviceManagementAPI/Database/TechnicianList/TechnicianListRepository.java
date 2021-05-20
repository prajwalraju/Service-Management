package com.serviceManagementAPI.Database.TechnicianList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TechnicianListRepository extends JpaRepository<TechnicianListEntity, Integer> {

    ArrayList<TechnicianListEntity> findAllByStatus(boolean b);

    @Query("FROM TechnicianListEntity WHERE technicianId = ?1 AND status = true")
    ArrayList<TechnicianListEntity> findByTechnicianAndStatus(int technicianId);
}
