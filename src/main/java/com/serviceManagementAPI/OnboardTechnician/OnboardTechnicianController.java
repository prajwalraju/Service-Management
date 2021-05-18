package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/onboardTechnician")
public class OnboardTechnicianController {
    @Autowired
    private TechnicianListRepository technicianListRepository;

    @GetMapping
    public List<TechnicianList> getTechnicianList() throws Exception {
        return technicianListRepository.findAllByStatus(true);
    }

    @PostMapping
    public TechnicianList addTechnicianList(@RequestBody TechnicianList technicianList) throws Exception {
        if (technicianListRepository.findById(technicianList.getTechnicianId()).isPresent()) {
            throw new ResourceAlreadyProcessedException("technician already present");
        } else {
            return technicianListRepository.save(technicianList);
        }
    }

    @PutMapping
    public TechnicianList updateTechnicianList(@RequestBody TechnicianList technicianList) throws Exception {

        TechnicianList existing = technicianListRepository.findById(technicianList.getTechnicianId())
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
        existing.setCenterId(technicianList.getCenterId());
        existing.setStatus(technicianList.getStatus());
        return technicianListRepository.save(existing);
    }

    @DeleteMapping
    public ResponseEntity<TechnicianList> delTechnicianList(@RequestParam(value = "technicianId") int technicianId) throws Exception {
        TechnicianList existing = technicianListRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));

        if (existing.getStatus() == true) {
            existing.setStatus(false);
            technicianListRepository.save(existing);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceBadException("Technician already disabled");
        }
    }
}
