package com.serviceManagementAPI.SearchTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import com.serviceManagementAPI.Database.CenterSchedule.CenterScheduleEntity;
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
    private SearchTechnicianService searchTechnicianService;


    @GetMapping
    public List<CenterScheduleEntity> getSearchTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        return searchTechnicianService.getSearchTechnician(centerSchedule);
    }
}




