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

@Service
public class OnboardCenterService {
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenterEntity> getServiceCenter() throws Exception {
        return serviceCenterRepository.findAllByStatus(true);
    }

    public ServiceCenterEntity addServiceCenter(ServiceCenter serviceCenter) throws Exception {
        ServiceCenterEntity serviceCenterEntity = new ServiceCenterEntity(serviceCenter.getCenterId(),
                serviceCenter.getLocation(),
                serviceCenter.getStartTime(),
                serviceCenter.getStopTime(),
                serviceCenter.isStatus());

        if (serviceCenterRepository.findById(serviceCenterEntity.getCenterId()).isPresent()) {
            throw new ResourceAlreadyProcessedException("Center already Present");
        } else {
            return serviceCenterRepository.save(serviceCenterEntity);
        }
    }

    public ServiceCenterEntity updateServiceCenter(ServiceCenter serviceCenter) throws Exception {
        ServiceCenterEntity existing = serviceCenterRepository.findById(serviceCenter.getCenterId())
                .orElseThrow(() -> new ResourceNotFoundException("Center not found"));

        existing.setLocation(serviceCenter.getLocation());
        existing.setStartTime(serviceCenter.getStartTime());
        existing.setStopTime(serviceCenter.getStopTime());
        existing.setStatus(serviceCenter.isStatus());
        return serviceCenterRepository.save(existing);
    }

    public ResponseEntity<ServiceCenterEntity> delServiceCenter(int centerId) throws Exception {
        ServiceCenterEntity existing = serviceCenterRepository.findById(centerId)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found to remove"));

        if (existing.getStatus()) {
            existing.setStatus(false);
            serviceCenterRepository.save(existing);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceBadException("Center already disabled");
        }
    }
}
