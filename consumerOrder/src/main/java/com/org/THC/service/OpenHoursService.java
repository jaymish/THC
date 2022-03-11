package com.org.THC.service;

import com.org.THC.model.OpenHours;

import java.util.List;

public interface OpenHoursService {
    boolean createOpenHours(OpenHours openHours);
    List<OpenHours> getAllOpenHours(String locationid);
    OpenHours getOpenHoursById(String id);
    OpenHours cancelOpenHours(String id);
    OpenHours activeOpenHours(String id);
    OpenHours updateOpenHours(OpenHours openHours);
}
