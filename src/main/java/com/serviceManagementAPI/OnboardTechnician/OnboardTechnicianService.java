package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardTechnicianService {
    @Autowired
    private TechnicianListRepository technicianListRepository;

    public List<TechnicianList> getTechnicianList() throws Exception {
        return new ArrayList<>(technicianListRepository.findAllByStatus(true));
    }

    public void addTechnicianList(TechnicianList technicianList) throws Exception {
        technicianListRepository.save(technicianList);
    }

    public void updateTechnicianList(TechnicianList technicianList) throws Exception {
        TechnicianList existing = technicianListRepository.findById(technicianList.getTechnicianId())
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
        existing.setCenterId(technicianList.getCenterId());
        existing.setStatus(technicianList.getStatus());
        technicianListRepository.save(existing);
    }

    public void delTechnicianList(int technicianId) throws Exception {
        TechnicianList existing = technicianListRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
        existing.setStatus(false);
        technicianListRepository.save(existing);
    }
}
