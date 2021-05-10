package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignTechnician")
public class AssignTechnicianController {
    @Autowired
    private AssignTechnicianService assignTechnicianService;

    @GetMapping
    public ResponseEntity<?> getAssignTechnician() throws Exception {
        return new ResponseEntity<>(assignTechnicianService.getAssignTechnician(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        assignTechnicianService.addAssignTechnician(centerSchedule);
        return new ResponseEntity<>(centerSchedule, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        assignTechnicianService.updateAssignTechnician(centerSchedule);
        return new ResponseEntity<>(centerSchedule, HttpStatus.ACCEPTED);
    }

    // Todo return a custom http response rather than a string
    @DeleteMapping
    public ResponseEntity<?> deleteAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        assignTechnicianService.deleteAssignTechnician(centerSchedule);
        return new ResponseEntity<>("Technician work is delete", HttpStatus.GONE);
    }
}
