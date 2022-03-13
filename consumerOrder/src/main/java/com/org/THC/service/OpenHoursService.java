package com.org.THC.service;

import com.org.THC.model.OpenHours;
import com.org.THC.model.PageOpenHours;

import java.util.List;

public interface OpenHoursService {
    boolean createOpenHours(OpenHours openHours);
    List<OpenHours> getAllOpenHours(String locationid);
    OpenHours getOpenHoursById(String id);
    OpenHours deactivateOpenHours(String id);
    OpenHours activateOpenHours(String id);
    OpenHours updateOpenHours(OpenHours openHours);
    PageOpenHours getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId);
}
