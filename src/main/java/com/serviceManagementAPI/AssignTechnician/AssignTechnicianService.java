package com.serviceManagementAPI.AssignTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignTechnicianService {
    @Autowired
    private CenterScheduleRepository centerScheduleRepository;

    public List<CenterSchedule> getAssignTechnician() throws Exception {
        return new ArrayList<>(centerScheduleRepository.findAll());
    }

    public void addAssignTechnician(CenterSchedule centerSchedule) throws Exception {
        centerScheduleRepository.save(centerSchedule);
    }

    public void updateAssignTechnician(CenterSchedule centerSchedule) throws Exception {
        centerScheduleRepository.save(centerSchedule);
    }

    public void deleteAssignTechnician(CenterSchedule centerSchedule) throws Exception {
        centerSchedule.setStatus(false);
        centerScheduleRepository.save(centerSchedule);
    }
}
