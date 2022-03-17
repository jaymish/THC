package com.org.THC.service;

import com.org.THC.model.APIExecutionTime;
import com.org.THC.model.APIName;
import com.org.THC.model.PageAPIExecuation;
import com.org.THC.repo.APIExecutionTimeRepo;
import com.org.THC.repo.APINameRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIExecutionService {
    private APIExecutionTimeRepo apiExecutionTimeRepo;
    private APINameRepo apiNameRepo;

    public APIExecutionService(APIExecutionTimeRepo apiExecutionTimeRepo,APINameRepo apiNameRepo){
        this.apiExecutionTimeRepo=apiExecutionTimeRepo;
        this.apiNameRepo=apiNameRepo;
    }

    public List<APIName> getAllNames(){
        return apiNameRepo.findAll();
    }

    public PageAPIExecuation getAllpage(Integer pageNo, Integer pageSize, String sortBy, String showbyname, String showbydate){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<APIExecutionTime> pagedResult;
        if (showbydate.equals("all")) {
            if (showbyname.equals("all")) {
                pagedResult = apiExecutionTimeRepo.findAll(paging);
            } else {
                pagedResult = apiExecutionTimeRepo.findByApiName_Name(showbyname, paging);
            }
        }
        else {
            if (showbyname.equals("all")) {
                pagedResult = apiExecutionTimeRepo.findByExecutionDate(showbydate, paging);
            } else {
                pagedResult = apiExecutionTimeRepo.findByExecutionDateAndApiName_Name(showbydate,showbyname, paging);
            }
        }
        PageAPIExecuation pageAPIExecuation = new PageAPIExecuation();
        if(pagedResult.hasContent()) {
            pageAPIExecuation.setApiExecutionTimeList(pagedResult.getContent());
            pageAPIExecuation.setPages(pagedResult.getTotalPages());
            return pageAPIExecuation;
        } else {
            pageAPIExecuation.setApiExecutionTimeList(null);
            pageAPIExecuation.setPages(0);
            return pageAPIExecuation;
        }
    }
}
