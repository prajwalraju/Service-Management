package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/onboardCenter")
public class OnboardCenterController {

    @Autowired
    private OnboardCenterService onboardCenterService;

    @GetMapping
    public List<ServiceCenterEntity> getServiceCenter() throws Exception {
        return onboardCenterService.getServiceCenter();
    }

    @PostMapping
    public ServiceCenterEntity addServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) throws Exception {
        return onboardCenterService.addServiceCenter(serviceCenter);
    }

    @PutMapping
    public ServiceCenterEntity updateServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) throws Exception {
        return onboardCenterService.updateServiceCenter(serviceCenter);

    }

    @DeleteMapping
    public ResponseEntity<ServiceCenterEntity> delServiceCenter(@Valid @RequestParam(value = "centerId") int centerId) throws Exception {
        return onboardCenterService.delServiceCenter(centerId);
    }
}


