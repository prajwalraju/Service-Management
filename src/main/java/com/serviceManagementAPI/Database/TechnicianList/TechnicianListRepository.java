package com.serviceManagementAPI.Database.TechnicianList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface TechnicianListRepository extends JpaRepository<TechnicianList, Integer> {

    ArrayList<TechnicianList> findAllByStatus(boolean Status);
}
