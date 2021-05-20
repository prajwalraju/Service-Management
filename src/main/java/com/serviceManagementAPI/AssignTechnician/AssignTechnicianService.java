package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleEntity;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Database.TechnicianList.TechnicianListRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignTechnicianService {

    @Autowired
    private CenterScheduleRepository centerScheduleRepository;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;
    @Autowired
    private TechnicianListRepository technicianListRepository;

    public CenterScheduleEntity addAssignTechnician(CenterSchedule centerSchedule) throws Exception {
        // find if there is any record with the center and technician on a given date
        if (centerScheduleRepository.findByTask(centerSchedule.getTechnicianId(),
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
                    return centerScheduleRepository.save(new CenterScheduleEntity(centerSchedule.getId(),
                            centerSchedule.getTechnicianId(),
                            centerSchedule.getCenterId(),
                            centerSchedule.getDate(),
                            centerSchedule.getStartTime(),
                            centerSchedule.getEndTime(),
                            centerSchedule.isStatus()));
                }
            }
        } else {
            throw new ResourceAlreadyProcessedException("Task already found");
        }


    }

    public CenterScheduleEntity updateAssignTechnician(CenterSchedule centerSchedule) throws Exception {

        Optional<CenterScheduleEntity> centerOptional = centerScheduleRepository.findByTask(centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate());
        if (centerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task not found");
        } else {
            if (serviceCenterRepository.ValidStartTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                throw new ResourceBadException("Start time before the center start time");
            }

            // find if the stop time is within bounds
            else if (serviceCenterRepository.ValidStopTime(centerSchedule.getCenterId(), centerSchedule.getEndTime()).isEmpty()) {
                throw new ResourceBadException("Stop time after the center stop time");
            } else {
                CenterScheduleEntity existingCenterSchedule = centerOptional.get();
                existingCenterSchedule.setStartTime(centerSchedule.getStartTime());
                existingCenterSchedule.setEndTime(centerSchedule.getEndTime());
                return centerScheduleRepository.save(existingCenterSchedule);
            }
        }
    }

    public ResponseEntity<CenterScheduleEntity> deleteAssignTechnician(CenterSchedule centerSchedule) throws Exception {
        Optional<CenterScheduleEntity> existingCenterSchedule = centerScheduleRepository.findByTask(centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate());

        if (existingCenterSchedule.isEmpty()) {
            throw new ResourceNotFoundException("Task not found");
        } else {
            CenterScheduleEntity centerScheduleEntity = existingCenterSchedule.get();
            if (centerScheduleEntity.isStatus()) {
                centerScheduleEntity.setStatus(false);
                centerScheduleRepository.save(centerScheduleEntity);
            } else {
                throw new ResourceAlreadyProcessedException("Task already disabled");
            }
        }
        return ResponseEntity.ok().build();

    }
}
