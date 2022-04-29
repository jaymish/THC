package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.THCApplication;
import com.org.THC.model.PageOpenHours;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.model.TimeModel;
import com.org.THC.service.LocationService;
import com.org.THC.service.ReservationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Reservation> reservationList;
    private LocationService locationService;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);
    @Value("${url.to.serverthc}")
    private String serverURL;

    String url="reservation/";

    @Autowired
    public DefaultReservationService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Reservation> reservationList,LocationService locationService){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.reservationList = reservationList;
        this.locationService=locationService;
    }

    @Override
    public boolean createReservations(String firstName, String lastName,  String phoneNumber, String emailId,int noOfPeople,String date,String time , String locationId){
        Reservation reservation=new Reservation();
        reservation.setFirstName(firstName);
        reservation.setLastName(lastName);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setDate(date);
        String[] timeStart = time.split(":");
        TimeModel timeModel=new TimeModel();
        timeModel.setHours(Integer.valueOf(timeStart[0]));
        timeModel.setMinutes(Integer.valueOf(timeStart[1]));
        if (timeModel.getHours()<12){
            timeModel.setAmPm("AM");
        }
        else {
            timeModel.setAmPm("PM");
            timeModel.setHours(timeModel.getHours()-12);
        }
        if(timeModel.getHours()==0){
            timeModel.setHours(12);
        }
        reservation.setTime(timeModel);
        reservation.setEmailId(emailId);
        reservation.setNoOfPeople(noOfPeople);
        reservation.setLocation(locationService.getLocationsById(locationId));
        logger.info("Service:User trying to save reservation "+date+" "+firstName+" "+lastName+" "+phoneNumber+" using microservices");
        return restTemplate.postForObject(serverURL+url+"create", reservation, boolean.class);

    }

    @Override
    public List<Reservation> getAllReservations(String id) {
        List<Reservation> reservationsList= restTemplate.getForObject(serverURL+url+"get-all?locationid="+id, reservationList.getClass());

        return reservationsList;
    }

    @Override
    public Reservation getReservationsById(String id) {
        return restTemplate.postForObject(serverURL+url+"get-by-id", id, Reservation.class);
    }

    @Override
    public List<Reservation> getReservationsByZip(int zip) {
        return restTemplate.postForObject(serverURL+url+"getByzip", zip, reservationList.getClass());
    }

    @Override
    public Reservation deactivateReservation(String id) {
        logger.info("Service:User trying to deactivate reservation with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"deactivate", id, Reservation.class);
    }

    @Override
    public Reservation activateReservation(String id) {
        logger.info("Service:User trying to activate reservation with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"activate", id, Reservation.class);
    }

    @Override
    public Reservation updateReservation(String id,String firstName, String lastName,  String phoneNumber, String emailId,int noOfPeople,String date,String time ,String status,String location) {
        Reservation reservation=new Reservation();
        reservation.setId(id);
        reservation.setFirstName(firstName);
        reservation.setLastName(lastName);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setDate(date);
        String[] timeStart = time.split(":");
        TimeModel timeModel=new TimeModel();
        timeModel.setHours(Integer.valueOf(timeStart[0]));
        timeModel.setMinutes(Integer.valueOf(timeStart[1]));
        if (timeModel.getHours()<12){
            timeModel.setAmPm("AM");
        }
        else {
            timeModel.setAmPm("PM");
            timeModel.setHours(timeModel.getHours()-12);
        }
        if(timeModel.getHours()==0){
            timeModel.setHours(12);
        }
        reservation.setTime(timeModel);
        reservation.setEmailId(emailId);
        reservation.setNoOfPeople(noOfPeople);
        reservation.setStatus(status);
        logger.info("Service:User trying to update and save reservation with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"update", reservation, Reservation.class);
    }

    @Override
    public PageReservation getAllpage(Integer pageNo, Integer pageSize, String locationId) {
        PageReservation pageReservation=restTemplate.getForObject(serverURL+url+"page?locationid="+locationId+"&pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id",PageReservation.class);
        return pageReservation;
    }
}
