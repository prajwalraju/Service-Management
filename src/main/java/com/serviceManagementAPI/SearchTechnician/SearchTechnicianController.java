package com.serviceManagementAPI.SearchTechnician;

import com.serviceManagementAPI.Database.CenterSchedule.CenterSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchTechnician")
public class SearchTechnicianController {
    @Autowired
    private SearchTechnicianService searchTechnicianService;

    //TODO alter the search technician and add multiple ways to search
    @GetMapping
    public ResponseEntity<?> getSearchTechnician(@RequestBody CenterSchedule centerSchedule) throws Exception {
        return new ResponseEntity<>(searchTechnicianService.getSearchTechnician(centerSchedule), HttpStatus.OK);
    }
}

