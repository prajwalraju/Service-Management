package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/assignTechnician")
public class AssignTechnicianController {
    @Autowired
    private AssignTechnicianService assignTechnicianService;
    @Autowired
    private CenterScheduleRepository centerScheduleRepository;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;
    @Autowired
    private TechnicianListRepository technicianListRepository;


    @PostMapping
    public CenterSchedule addAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        // find if there is any record with the center and technician on a given date
        if (centerScheduleRepository.findByTechnicianIdAndCenterId(centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate()).isEmpty()) {

            // find if the center is valid
            if (serviceCenterRepository.findByCenterAndStatus(centerSchedule.getCenterId()).isEmpty()) {
                throw new ResourceNotFoundException("Center not found");
            }

            // find if the technician is valid
            else if (technicianListRepository.findByTechnicianAndStatus(centerSchedule.getTechnicianId()).isEmpty()) {
                throw new ResourceNotFoundException("Technician not found");
            } else {
                // find if the start time is within bounds
                if (serviceCenterRepository.ValidStartTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                    throw new ResourceBadException("Start time before the center start time");
                }

                // find if the stop time is within bounds
                else if (serviceCenterRepository.ValidStopTime(centerSchedule.getCenterId(), centerSchedule.getEndTime()).isEmpty()) {
                    throw new ResourceBadException("Stop time after the center stop time");
                }

                // if all the conditions satisfies then save the task
                else {
                    return centerScheduleRepository.save(centerSchedule);
                }
            }
        } else {
            throw new ResourceAlreadyProcessedException("Task already found");
        }


    }

    @PutMapping
    public CenterSchedule updateAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        if (centerScheduleRepository.findByTechnicianIdAndCenterId(centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate()).isEmpty()) {
            throw new ResourceNotFoundException("Task not found");
        } else {
            if (serviceCenterRepository.ValidStartTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                throw new ResourceBadException("Start time before the center start time");
            }

            // find if the stop time is within bounds
            else if (serviceCenterRepository.ValidStopTime(centerSchedule.getCenterId(), centerSchedule.getEndTime()).isEmpty()) {
                throw new ResourceBadException("Stop time after the center stop time");
            } else {
                CenterSchedule existingCenterSchedule = centerScheduleRepository.findByTask(centerSchedule.getTechnicianId(),
                        centerSchedule.getCenterId(),
                        centerSchedule.getDate());
                existingCenterSchedule.setStartTime(centerSchedule.getStartTime());
                existingCenterSchedule.setEndTime(centerSchedule.getEndTime());
                return centerScheduleRepository.save(existingCenterSchedule);
            }
        }
    }

    @DeleteMapping
    public ResponseEntity<CenterSchedule> deleteAssignTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        if (centerScheduleRepository.findByTechnicianIdAndCenterId(centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate()).isEmpty()) {
            throw new ResourceNotFoundException("Task not found");
        } else {
            CenterSchedule existingCenterSchedule = centerScheduleRepository.findByTask(centerSchedule.getTechnicianId(),
                    centerSchedule.getCenterId(),
                    centerSchedule.getDate());
            if (existingCenterSchedule.isStatus()) {
                existingCenterSchedule.setStatus(false);
            } else {
                throw new ResourceAlreadyProcessedException("Task already disabled");
            }
        }

        assignTechnicianService.deleteAssignTechnician(centerSchedule);
        return ResponseEntity.ok().build();
    }
}
