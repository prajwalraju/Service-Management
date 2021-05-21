package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// An controller to onboard service centers
@RestController
@RequestMapping("/v1/onboardCenter")
public class OnboardCenterController {

    // Auto wire service center serve
    @Autowired
    private OnboardCenterService onboardCenterService;

    // Map get request to service center
    @GetMapping
    public List<ServiceCenterEntity> getServiceCenter() throws Exception {
        return onboardCenterService.getServiceCenter();
    }

    // Map post request to service center
    @PostMapping
    public ServiceCenterEntity addServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) throws Exception {
        return onboardCenterService.addServiceCenter(serviceCenter);
    }

    // Map put request to service center
    @PutMapping
    public ServiceCenterEntity updateServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) throws Exception {
        return onboardCenterService.updateServiceCenter(serviceCenter);

    }

    // Map delete request to service center
    @DeleteMapping
    public ResponseEntity<ServiceCenterEntity> delServiceCenter(@Valid @RequestParam(value = "centerId") int centerId) throws Exception {
        return onboardCenterService.delServiceCenter(centerId);
    }
}


