package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardTechnicianService {
    @Autowired
    private TechnicianListRepository technicianListRepository;

    public List<TechnicianList> getTechnicianList() throws Exception {
        return new ArrayList<>(technicianListRepository.findAll());
    }

    public TechnicianList addTechnicianList(TechnicianList technicianList) throws Exception {
        technicianListRepository.save(technicianList);
        return technicianList;
    }

    public TechnicianList updateTechnicianList(TechnicianList technicianList) throws Exception {
        technicianListRepository.save(technicianList);
        return technicianList;
    }

    //TODO soft delete
    public void delTechnicianList(int technicianId) throws Exception {
        technicianListRepository.deleteById(technicianId);
    }
}
