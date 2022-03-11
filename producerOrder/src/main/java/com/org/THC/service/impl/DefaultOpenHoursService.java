package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.model.OpenHours;
import com.org.THC.model.TimeModel;
import com.org.THC.service.LocationService;
import com.org.THC.service.OpenHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultOpenHoursService implements OpenHoursService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<OpenHours> listOpenHours;
    private LocationService locationService;
    String url="http://localhost:8081/openHours/";

    @Autowired
    public DefaultOpenHoursService(RestTemplate restTemplate, ObjectMapper objectMapper, List<OpenHours> listOpenHours,LocationService locationService){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.listOpenHours = listOpenHours;
        this.locationService=locationService;
    }

    @Override
    public boolean createOpenHours(String day,  String startTime,String endTime,String locationId){
        System.out.println("addopenHours");
        OpenHours openHours=new OpenHours();
        openHours.setDay(day);
        String[] timeStart = startTime.split(":");
        System.out.println(timeStart[0]);
        TimeModel start=new TimeModel();
        start.setHours(Integer.valueOf(timeStart[0]));
        start.setMinutes(Integer.valueOf(timeStart[1]));
        if (start.getHours()<12){
            start.setAmPm("AM");
        }
        else {
            start.setAmPm("PM");
            start.setHours(start.getHours()-12);
        }
        if(start.getHours()==0){
            start.setHours(12);
        }
        String[] timeEnd = endTime.split(":");
        TimeModel end=new TimeModel();
        end.setHours(Integer.valueOf(timeEnd[0]));
        end.setMinutes(Integer.valueOf(timeEnd[1]));
        if (end.getHours()<12){
            end.setAmPm("AM");
        }
        else {
            end.setAmPm("PM");
            end.setHours(end.getHours()-12);
        }
        if(end.getHours()==0){
            end.setHours(12);
        }
        openHours.setStartTime(start);
        openHours.setEndTime(end);
        openHours.setLocation(locationService.getLocationsById(locationId));
        System.out.println("Day:"+day+"  Start:"+startTime+"  End"+endTime);
        return restTemplate.postForObject(url+"create", openHours, boolean.class);

    }

    @Override
    public List<OpenHours> getAllOpenHours(String id) {
        List<OpenHours> openHoursList= restTemplate.postForObject(url+"getAll",id, listOpenHours.getClass());

        return openHoursList;
    }

    @Override
    public OpenHours getOpenHoursById(String id) {
        return restTemplate.postForObject(url+"getById", id, OpenHours.class);
    }

    @Override
    public List<OpenHours> getOpenHoursByZip(int zip) {
        return restTemplate.postForObject(url+"getByzip", zip, listOpenHours.getClass());
    }

    @Override
    public OpenHours cancelOpenHours(String id) {
        return restTemplate.postForObject(url+"cancel", id, OpenHours.class);
    }

    @Override
    public OpenHours activeOpenHours(String id) {
        return restTemplate.postForObject(url+"active", id, OpenHours.class);
    }

    @Override
    public OpenHours updateOpenHours(String id, String day,  String startTime,String endTime,String locationId) {
        OpenHours openHours=new OpenHours();
        openHours.setId(id);
        openHours.setDay(day);
        String[] timeStart = startTime.split(":");
        System.out.println(timeStart[0]);
        TimeModel start=new TimeModel();
        start.setHours(Integer.valueOf(timeStart[0]));
        start.setMinutes(Integer.valueOf(timeStart[1]));
        if (start.getHours()<12){
            start.setAmPm("AM");
        }
        else {
            start.setAmPm("PM");
            start.setHours(start.getHours()-12);
        }
        if(start.getHours()==0){
            start.setHours(12);
        }
        String[] timeEnd = endTime.split(":");
        TimeModel end=new TimeModel();
        end.setHours(Integer.valueOf(timeEnd[0]));
        end.setMinutes(Integer.valueOf(timeEnd[1]));
        if (end.getHours()<12){
            end.setAmPm("AM");
        }
        else {
            end.setAmPm("PM");
            end.setHours(end.getHours()-12);
        }
        if(end.getHours()==0){
            end.setHours(12);
        }
        openHours.setStartTime(start);
        openHours.setEndTime(end);
        return restTemplate.postForObject(url+"update", openHours, OpenHours.class);
    }
}
