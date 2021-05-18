package com.serviceManagementAPI.SearchTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Exception.ResourceBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/searchTechnician")
public class SearchTechnicianController {
    @Autowired
    private CenterScheduleRepository centerScheduleRepository;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @GetMapping
    public List<CenterSchedule> getSearchTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        if (centerSchedule.isCenter()) {
            if (centerSchedule.isDate()) {
                if (centerSchedule.isStartTime()) {
                    if (serviceCenterRepository.ValidStartTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                        throw new ResourceBadException("start time out of bound");
                    } else if (serviceCenterRepository.ValidCustomerStopTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                        throw new ResourceBadException("end time close to service center close time");
                    } else {
                        return centerScheduleRepository.findAllByCenterIdAndDateAndTime(centerSchedule.getCenterId(), centerSchedule.getDate(), centerSchedule.getStartTime());
                    }
                } else {
                    return centerScheduleRepository.findAllByCenterIdAndDate(centerSchedule.getCenterId(), centerSchedule.getDate());
                }
            } else {
                if (centerSchedule.isStartTime()) {
                    if (serviceCenterRepository.ValidStartTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                        throw new ResourceBadException("start time out of bound");
                    } else if (serviceCenterRepository.ValidCustomerStopTime(centerSchedule.getCenterId(), centerSchedule.getStartTime()).isEmpty()) {
                        throw new ResourceBadException("end time close to service center close time");
                    } else {
                        return centerScheduleRepository.findAllByCenterIdAndTime(centerSchedule.getCenterId(), centerSchedule.getStartTime());
                    }
                } else {
                    return centerScheduleRepository.findAllByCenter(centerSchedule.getCenterId());
                }
            }
        } else {
            if (centerSchedule.isDate()) {
                if (centerSchedule.isStartTime()) {
                    return centerScheduleRepository.findAllByDateAndTime(centerSchedule.getDate(), centerSchedule.getStartTime());
                } else {
                    return centerScheduleRepository.findAllByDate(centerSchedule.getDate());
                }
            } else {
                return centerScheduleRepository.findAllByTime(centerSchedule.getStartTime());
            }
        }
    }
}

//center date time
//    0   0   1   find by time
//    0   1   0   find by date
//    0   1   1   find by date and time
//    1   0   0   find by center
//    1   0   1   find by center and time
//    1   1   0   find by center and date
//    1   1   1   find by center , date and time
