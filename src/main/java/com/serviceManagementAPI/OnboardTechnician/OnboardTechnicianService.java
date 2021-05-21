package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListEntity;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Service for assigning tasks to technicians
@Service
public class OnboardTechnicianService {

    // Auto wire technician list repo
    @Autowired
    private TechnicianListRepository technicianListRepository;

    // Auto wire service center repo
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;


    public List<TechnicianListEntity> getTechnicianList() throws Exception {
        return technicianListRepository.findAllByStatus(true);
    }

    public TechnicianListEntity addTechnicianList(TechnicianList technicianList) throws Exception {
        TechnicianListEntity technicianListEntity = new TechnicianListEntity(technicianList.getTechnicianId(),
                technicianList.getCenterId(),
                technicianList.isStatus());
        if (serviceCenterRepository.findByCenterAndStatus(technicianListEntity.getCenterId()).isEmpty()) {
            throw new ResourceNotFoundException("Center not found");
        } else {
            if (technicianListRepository.findById(technicianListEntity.getTechnicianId()).isPresent()) {
                throw new ResourceAlreadyProcessedException("technician already present");
            } else {
                return technicianListRepository.save(technicianListEntity);
            }
        }
    }

    public TechnicianListEntity updateTechnicianList(TechnicianList technicianList) throws Exception {
        if (serviceCenterRepository.findByCenterAndStatus(technicianList.getCenterId()).isEmpty()) {
            throw new ResourceNotFoundException("Center not found");
        } else {
            TechnicianListEntity existing = technicianListRepository.findById(technicianList.getTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
            existing.setCenterId(technicianList.getCenterId());
            existing.setStatus(technicianList.isStatus());
            return technicianListRepository.save(existing);
        }
    }

    public ResponseEntity<TechnicianListEntity> delTechnicianList(int technicianId) throws Exception {
        TechnicianListEntity existing = technicianListRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));

        if (existing.getStatus()) {
            existing.setStatus(false);
            technicianListRepository.save(existing);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceBadException("Technician already disabled");
        }
    }

}
