package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/onboardCenter")
public class OnboardCenterController {

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;


    @GetMapping
    public List<ServiceCenter> getServiceCenter() throws Exception {
        return serviceCenterRepository.findAllByStatus(true);
    }

    @PostMapping
    public ServiceCenter addServiceCenter(@RequestBody ServiceCenter serviceCenter) throws Exception {
        if (serviceCenterRepository.findById(serviceCenter.getCenterId()).isPresent()) {
            throw new ResourceAlreadyProcessedException("Center already Present");
        } else {
            return serviceCenterRepository.save(serviceCenter);
        }

    }

    @PutMapping
    public ServiceCenter updateServiceCenter(@RequestBody ServiceCenter serviceCenter) throws Exception {


        ServiceCenter existing = serviceCenterRepository.findById(serviceCenter.getCenterId())
                .orElseThrow(() -> new ResourceNotFoundException("Center not found"));
        existing.setLocation(serviceCenter.getLocation());
        existing.setStartTime(serviceCenter.getStartTime());
        existing.setStopTime(serviceCenter.getStopTime());
        existing.setStatus(serviceCenter.getStatus());
        return serviceCenterRepository.save(existing);

    }

    @DeleteMapping
    public ResponseEntity<ServiceCenter> delServiceCenter(@RequestParam(value = "centerId") int centerId) throws Exception {
        ServiceCenter existing = serviceCenterRepository.findById(centerId)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found to remove"));

        if (existing.getStatus() == true) {
            existing.setStatus(false);
            serviceCenterRepository.save(existing);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceBadException("Center already disabled");
        }
    }
}


