package com.org.THC.service.impl;

import com.org.THC.model.OpenHours;
import com.org.THC.repo.OpenHoursRepo;
import com.org.THC.service.OpenHoursService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOpenHoursService implements OpenHoursService {

    private OpenHoursRepo openHoursRepo;
    private EmailServiceImpl emailService;

    public DefaultOpenHoursService(OpenHoursRepo openHoursRepo, EmailServiceImpl emailService){
        this.openHoursRepo = openHoursRepo;
        this.emailService = emailService;
    }
    @Override
    public boolean createOpenHours(OpenHours openHours) {
        System.out.println(openHours);
        openHoursRepo.saveOpenHours(openHours);
        //emailService.sendSimpleMessage(openHours.getCustomer().getEmail_id(),"OpenHours Created","Thank you your openHours was created. Here is the detail of your openHours \n"+ openHours.toString());
        return true;
    }

    @Override
    public List<OpenHours> getAllOpenHours(String locationid) {
        List<OpenHours> openHoursList = openHoursRepo.getAllOpenHours(locationid);

        return openHoursList;
    }

    @Override
    public OpenHours getOpenHoursById(String id){
        OpenHours openHours = openHoursRepo.getOpenHoursById(id);
        return openHours;
    }


    @Override
    public OpenHours cancelOpenHours(String id) {
        //logic to check if cancel is possible
        return openHoursRepo.openHoursCancel(id);
    }

    @Override
    public OpenHours activeOpenHours(String id) {
        //logic to check if cancel is possible
        return openHoursRepo.openHoursActive(id);
    }

    @Override
    public OpenHours updateOpenHours(OpenHours openHours) {
        return openHoursRepo.updateOpenHours(openHours);
    }
}
