package com.serviceManagementAPI.Database.TechnicianList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TechnicianListRepository extends JpaRepository<TechnicianList, Integer> {

    ArrayList<TechnicianList> findAllByStatus(boolean b);

    @Query("FROM TechnicianList WHERE technicianId = ?1 AND status = true")
    ArrayList<TechnicianList> findByTechnicianAndStatus(int technicianId);
}
