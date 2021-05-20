package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/assignTechnician")
public class AssignTechnicianController {

    @Autowired
    private AssignTechnicianService assignTechnicianService;


    @PostMapping
    public CenterScheduleEntity addAssignTechnician(@Valid @RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.addAssignTechnician(centerSchedule);
    }

    @PutMapping
    public CenterScheduleEntity updateAssignTechnician(@Valid @RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.updateAssignTechnician(centerSchedule);
    }

    @DeleteMapping
    public ResponseEntity<CenterScheduleEntity> deleteAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        return assignTechnicianService.deleteAssignTechnician(centerSchedule);
    }
}
