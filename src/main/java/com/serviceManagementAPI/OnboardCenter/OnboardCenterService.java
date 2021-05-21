package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterEntity;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Exception.ResourceAlreadyProcessedException;
import com.serviceManagementAPI.Exception.ResourceBadException;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// service for on boarding the service centers
@Service
public class OnboardCenterService {

    //AUTPo wire service center repo
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    // method to get all the service centers onboarded and active
    public List<ServiceCenterEntity> getServiceCenter() throws Exception {
        return serviceCenterRepository.findAllByStatus(true);
    }

    // method to add a new service center
    public ServiceCenterEntity addServiceCenter(ServiceCenter serviceCenter) throws Exception {

        // build a entity object of service center using the client service center details
        ServiceCenterEntity serviceCenterEntity = new ServiceCenterEntity(serviceCenter.getCenterId(),
                serviceCenter.getLocation(),
                serviceCenter.getStartTime(),
                serviceCenter.getStopTime(),
                serviceCenter.isStatus());

        // check if the service center is already present
        if (serviceCenterRepository.findById(serviceCenterEntity.getCenterId()).isPresent()) {
            throw new ResourceAlreadyProcessedException("Center already Present");
        } else {

            // save the new service center
            return serviceCenterRepository.save(serviceCenterEntity);
        }
    }

    // method to update existing service center
    public ServiceCenterEntity updateServiceCenter(ServiceCenter serviceCenter) throws Exception {

        // check if the center is existing
        ServiceCenterEntity existing = serviceCenterRepository.findById(serviceCenter.getCenterId())
                .orElseThrow(() -> new ResourceNotFoundException("Center not found"));

        // set the updated parameters
        existing.setLocation(serviceCenter.getLocation());
        existing.setStartTime(serviceCenter.getStartTime());
        existing.setStopTime(serviceCenter.getStopTime());
        existing.setStatus(serviceCenter.isStatus());

        // save the updated parameters to the existing service center
        return serviceCenterRepository.save(existing);
    }

    // method to soft delete the service center
    public ResponseEntity<ServiceCenterEntity> delServiceCenter(int centerId) throws Exception {

        // check if the service center is existing
        ServiceCenterEntity existing = serviceCenterRepository.findById(centerId)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found to remove"));

        // check if the service center is active
        if (existing.getStatus()) {
            existing.setStatus(false);

            // set the status of the service center as false
            serviceCenterRepository.save(existing);

            // return a HTTP status as ok
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceBadException("Center already disabled");
        }
    }
}
