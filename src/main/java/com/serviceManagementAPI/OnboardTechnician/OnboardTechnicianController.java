package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/onboardTechnician")
public class OnboardTechnicianController {
    @Autowired
    private OnboardTechnicianService onboardTechnicianService;

    @GetMapping
    public List<TechnicianListEntity> getTechnicianList() throws Exception {
        return onboardTechnicianService.getTechnicianList();
    }

    @PostMapping
    public TechnicianListEntity addTechnicianList(@Valid @RequestBody TechnicianList technicianList) throws Exception {
        return onboardTechnicianService.addTechnicianList(technicianList);
    }

    @PutMapping
    public TechnicianListEntity updateTechnicianList(@Valid @RequestBody TechnicianList technicianList) throws Exception {
        return onboardTechnicianService.updateTechnicianList(technicianList);
    }

    @DeleteMapping
    public ResponseEntity<TechnicianListEntity> delTechnicianList(@RequestParam(value = "technicianId") int technicianId) throws Exception {
        return onboardTechnicianService.delTechnicianList(technicianId);
    }
}
