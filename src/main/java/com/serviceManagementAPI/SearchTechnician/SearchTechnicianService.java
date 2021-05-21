package com.serviceManagementAPI.SearchTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleEntity;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Exception.ResourceBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchTechnicianService {
    @Autowired
    private CenterScheduleRepository centerScheduleRepository;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<CenterScheduleEntity> getSearchTechnician(CenterSchedule centerSchedule) {

        CenterScheduleEntity centerScheduleEntity = new CenterScheduleEntity(centerSchedule.getId(),
                centerSchedule.getTechnicianId(),
                centerSchedule.getCenterId(),
                centerSchedule.getDate(),
                centerSchedule.getStartTime(),
                centerSchedule.getEndTime(),
                centerSchedule.isStatus());
//center date time
//    0   0   1   find by time
//    0   1   0   find by date
//    0   1   1   find by date and time
//    1   0   0   find by center
//    1   0   1   find by center and time
//    1   1   0   find by center and date
//    1   1   1   find by center, date and time

        if (centerScheduleEntity.isCenter()) {
            if (centerScheduleEntity.isDate()) {
                if (centerScheduleEntity.isStartTime()) {
                    return ValidateFindAllByCenterAndTime(centerScheduleEntity);
                } else {
                    return centerScheduleRepository.findAllByCenterIdAndDate(centerScheduleEntity.getCenterId(), centerScheduleEntity.getDate());
                }
            } else {
                if (centerScheduleEntity.isStartTime()) {
                    return ValidateFindAllByCenterAndDateAndTime(centerScheduleEntity);
                } else {
                    return centerScheduleRepository.findAllByCenter(centerScheduleEntity.getCenterId());
                }
            }
        } else {
            if (centerScheduleEntity.isDate()) {
                if (centerScheduleEntity.isStartTime()) {
                    return centerScheduleRepository.findAllByDateAndTime(centerScheduleEntity.getDate(), centerScheduleEntity.getStartTime());
                } else {
                    return centerScheduleRepository.findAllByDate(centerScheduleEntity.getDate());
                }
            } else {
                return centerScheduleRepository.findAllByTime(centerScheduleEntity.getStartTime());
            }
        }
    }

    private List<CenterScheduleEntity> ValidateFindAllByCenterAndDateAndTime(CenterScheduleEntity centerScheduleEntity) {
        if (serviceCenterRepository.ValidStartTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getStartTime()).isEmpty()) {
            throw new ResourceBadException("start time out of bound");
        } else if (serviceCenterRepository.ValidCustomerStopTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getStartTime()).isEmpty()) {
            throw new ResourceBadException("end time close to service center close time");
        } else {
            return centerScheduleRepository.findAllByCenterIdAndDateAndTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getDate(), centerScheduleEntity.getStartTime());
        }
    }

    private List<CenterScheduleEntity> ValidateFindAllByCenterAndTime(CenterScheduleEntity centerScheduleEntity) {
        if (serviceCenterRepository.ValidStartTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getStartTime()).isEmpty()) {
            throw new ResourceBadException("start time out of bound");
        } else if (serviceCenterRepository.ValidCustomerStopTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getStartTime()).isEmpty()) {
            throw new ResourceBadException("end time close to service center close time");
        } else {
            return centerScheduleRepository.findAllByCenterIdAndTime(centerScheduleEntity.getCenterId(), centerScheduleEntity.getStartTime());
        }
    }

}
