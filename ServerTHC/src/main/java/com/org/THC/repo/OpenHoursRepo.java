package com.org.THC.repo;

import com.org.THC.model.OpenHours;

import java.util.List;

public interface OpenHoursRepo {
    List<OpenHours> getAllOpenHours(String locationid);
    OpenHours getOpenHoursById(String openHoursId);
    OpenHours saveOpenHours(OpenHours openHours);
    OpenHours openHoursDeactivate(String id);
    OpenHours openHoursActivate(String id);
    OpenHours updateOpenHours(OpenHours openHours);
}
