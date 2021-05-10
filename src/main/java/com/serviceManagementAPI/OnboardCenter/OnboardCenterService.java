package com.serviceManagementAPI.OnboardCenter;

import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenter;
import com.serviceManagementAPI.Database.ServiceCenter.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardCenterService {
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;
    private Object ArrayList;

    public List<ServiceCenter> getServiceCenter() throws Exception {
        return new ArrayList<>(serviceCenterRepository.findAll());
    }

    public void addServiceCenter(ServiceCenter serviceCenter) throws Exception {
        serviceCenterRepository.save(serviceCenter);
    }

    public void updateServiceCenter(ServiceCenter serviceCenter) throws Exception {
        serviceCenterRepository.save(serviceCenter);
    }

    //todo soft delete
    public void delServiceCenter(int centerId) throws Exception {
        serviceCenterRepository.deleteById(centerId);
    }
}
