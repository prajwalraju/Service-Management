package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// An controller to onboard technicians
@RestController
@RequestMapping("/v1/onboardTechnician")
public class OnboardTechnicianController {

    // Auto Wire technician service
    @Autowired
    private OnboardTechnicianService onboardTechnicianService;

    // Map get request to technician list
    @GetMapping
    public List<TechnicianListEntity> getTechnicianList() throws Exception {
        return onboardTechnicianService.getTechnicianList();
    }

    // Map post request to technician
    @PostMapping
    public TechnicianListEntity addTechnicianList(@Valid @RequestBody TechnicianList technicianList) throws Exception {
        return onboardTechnicianService.addTechnicianList(technicianList);
    }

    // Map put request to technician
    @PutMapping
    public TechnicianListEntity updateTechnicianList(@Valid @RequestBody TechnicianList technicianList) throws Exception {
        return onboardTechnicianService.updateTechnicianList(technicianList);
    }

    // Map delete request to technician
    @DeleteMapping
    public ResponseEntity<TechnicianListEntity> delTechnicianList(@RequestParam(value = "technicianId") int technicianId) throws Exception {
        return onboardTechnicianService.delTechnicianList(technicianId);
    }
}
