package com.serviceManagementAPI.SearchTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchTechnicianService {
    @Autowired
    private CenterScheduleRepository centerScheduleRepository;

    public List<CenterSchedule> getSearchTechnician(CenterSchedule centerSchedule) throws Exception {
        return new ArrayList<>(centerScheduleRepository.findAllByCenterIdAndDate(centerSchedule.getCenterId(), centerSchedule.getDate()));
    }
}
