package com.org.THC.service.impl;

import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.OpenHours;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageOpenHours;
import com.org.THC.repo.OpenHoursRepo;
import com.org.THC.repo.PageOpenHoursRepo;
import com.org.THC.service.OpenHoursService;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOpenHoursService implements OpenHoursService {

    private OpenHoursRepo openHoursRepo;
    private EmailServiceImpl emailService;
    private PageOpenHoursRepo pageOpenHoursRepo;
    //private static final Logger logger = LogManager.getLogger(THCApplication.class);

    public DefaultOpenHoursService(OpenHoursRepo openHoursRepo, EmailServiceImpl emailService,PageOpenHoursRepo pageOpenHoursRepo){
        this.openHoursRepo = openHoursRepo;
        this.emailService = emailService;
        this.pageOpenHoursRepo=pageOpenHoursRepo;
    }
    @Override
    public boolean createOpenHours(OpenHours openHours) {
        //logger.info("Service:User trying to save openHours "+openHours.getDay()+" "+openHours.getStartTime()+" "+openHours.getEndTime());
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
    public OpenHours deactivateOpenHours(String id) {
        //logic to check if deactivate is possible
        //logger.info("Service:User trying to deactivate openHours with id: "+id);
        return openHoursRepo.openHoursDeactivate(id);
    }

    @Override
    public OpenHours activateOpenHours(String id) {
        //logic to check if deactivate is possible
        //logger.info("Service:User trying to activate openHours with id: "+id);
        return openHoursRepo.openHoursActivate(id);
    }

    @Override
    public OpenHours updateOpenHours(OpenHours openHours) {
        //logger.info("Service:User trying to update and save openHours with id: "+openHours.getId());
        return openHoursRepo.updateOpenHours(openHours);
    }

    @Override
    public PageOpenHours getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<OpenHours> pagedResult = pageOpenHoursRepo.findByLocation_Id(locationId,paging);
        PageOpenHours pageOpenHours = new PageOpenHours();
        if(pagedResult.hasContent()) {
            pageOpenHours.setOpenHoursList(pagedResult.getContent());
            pageOpenHours.setPages(pagedResult.getTotalPages());
            return pageOpenHours;
        } else {
            pageOpenHours.setOpenHoursList(null);
            pageOpenHours.setPages(0);
            return pageOpenHours;
        }
    }
}
