package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboardCenter")
public class OnboardCenterController {
    @Autowired
    private OnboardCenterService onboardCenterService;


    @GetMapping
    public ResponseEntity<?> getServiceCenter() throws Exception {
        return new ResponseEntity<>(onboardCenterService.getServiceCenter(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addServiceCenter(@RequestBody ServiceCenter serviceCenter) throws Exception {
        onboardCenterService.addServiceCenter(serviceCenter);
        return new ResponseEntity<>(serviceCenter, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateServiceCenter(@RequestBody ServiceCenter serviceCenter) throws Exception {
        onboardCenterService.updateServiceCenter(serviceCenter);
        return new ResponseEntity<>(serviceCenter, HttpStatus.ACCEPTED);
    }

    // TODO return a custom http response rather than a string
    @DeleteMapping
    public ResponseEntity<?> delServiceCenter(@RequestParam(value = "centerId") int centerId) throws Exception {
        onboardCenterService.delServiceCenter(centerId);
        return new ResponseEntity<>("Center Removed", HttpStatus.GONE);
    }
}
