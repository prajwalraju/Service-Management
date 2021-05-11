package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import com.serviceManagementAPI.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardCenterService {
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenter> getServiceCenter() throws Exception {
        return new ArrayList<>(serviceCenterRepository.findAll());
    }

    public void addServiceCenter(ServiceCenter serviceCenter) throws Exception {
        serviceCenterRepository.save(serviceCenter);
    }

    public void updateServiceCenter(ServiceCenter serviceCenter) throws Exception {
        ServiceCenter existing = serviceCenterRepository.findById(serviceCenter.getCenterId())
                .orElseThrow(() -> new ResourceNotFoundException("Center not found"));
        existing.setLocation(serviceCenter.getLocation());
        existing.setStartTime(serviceCenter.getStartTime());
        existing.setStopTime(serviceCenter.getStopTime());
        existing.setStatus(serviceCenter.getStatus());
        serviceCenterRepository.save(existing);
    }

    public void delServiceCenter(int centerId) throws Exception {
        ServiceCenter existing = serviceCenterRepository.findById(centerId)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found"));
        existing.setStatus(false);
        serviceCenterRepository.save(existing);
    }
}
