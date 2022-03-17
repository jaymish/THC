package com.org.THC.service;

import com.org.THC.model.OpenHours;
import com.org.THC.model.PageOpenHours;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface OpenHoursService {

    boolean createOpenHours(String day,  String startTime,String endTime,String locationId);
    List<OpenHours> getAllOpenHours(String id);
    OpenHours getOpenHoursById(String id);
    List<OpenHours> getOpenHoursByZip(int zip);
    OpenHours deactivateOpenHours(String id);
    OpenHours activateOpenHours(String id);
    OpenHours updateOpenHours(String id,String day,  String startTime,String endTime,String locationId);
    PageOpenHours getAllpage(Integer pageNo, Integer pageSize, String locationId);
}
