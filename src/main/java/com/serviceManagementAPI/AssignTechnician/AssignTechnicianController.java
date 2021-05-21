package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// An controller to map task assigned to a technician
@RestController
@RequestMapping("/v1/assignTechnician")
public class AssignTechnicianController {

    // Auto Wire technician service
    @Autowired
    private AssignTechnicianService assignTechnicianService;

    // Map post request to add tasks to technicians
    @PostMapping
    public CenterScheduleEntity addAssignTechnician(@Valid @RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.addAssignTechnician(centerSchedule);
    }

    // Map put request to update tasks to technicians
    @PutMapping
    public CenterScheduleEntity updateAssignTechnician(@Valid @RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.updateAssignTechnician(centerSchedule);
    }

    // Map delete request to delete tasks of technicians
    @DeleteMapping
    public ResponseEntity<CenterScheduleEntity> deleteAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.deleteAssignTechnician(centerSchedule);
    }
}
