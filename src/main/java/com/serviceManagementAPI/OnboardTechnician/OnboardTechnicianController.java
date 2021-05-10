package com.serviceManagementAPI.OnboardTechnician;

import com.serviceManagementAPI.Database.TechnicianList.TechnicianList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboardTechnician")
public class OnboardTechnicianController {
    @Autowired
    private OnboardTechnicianService technicianService;

    @GetMapping
    public ResponseEntity<?> getTechnicianList() throws Exception {
        return new ResponseEntity<>(technicianService.getTechnicianList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTechnicianList(@RequestBody TechnicianList technicianList) throws Exception {
        technicianService.addTechnicianList(technicianList);
        return new ResponseEntity<>(technicianList, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateTechnicianList(@RequestBody TechnicianList technicianList) throws Exception {
        technicianService.updateTechnicianList(technicianList);
        return new ResponseEntity<>(technicianList, HttpStatus.ACCEPTED);
    }

    // TODO return a custom http response rather than a string
    @DeleteMapping
    public ResponseEntity<?> delTechnicianList(@RequestParam(value = "technicianId") int technicianId) throws Exception {
        technicianService.delTechnicianList(technicianId);
        return new ResponseEntity<>(technicianId, HttpStatus.GONE);
    }
}
