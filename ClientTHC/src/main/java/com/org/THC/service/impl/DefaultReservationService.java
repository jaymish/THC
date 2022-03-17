package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.model.PageOpenHours;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.model.TimeModel;
import com.org.THC.service.LocationService;
import com.org.THC.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Reservation> reservationList;
    private LocationService locationService;
    String url="http://localhost:8081/reservation/";

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
        return restTemplate.postForObject(url+"create", reservation, boolean.class);

    }

    @Override
    public List<Reservation> getAllReservations(String id) {
        List<Reservation> reservationsList= restTemplate.getForObject(url+"get-all?locationid="+id, reservationList.getClass());

        return reservationsList;
    }

    @Override
    public Reservation getReservationsById(String id) {
        return restTemplate.postForObject(url+"get-by-id", id, Reservation.class);
    }

    @Override
    public List<Reservation> getReservationsByZip(int zip) {
        return restTemplate.postForObject(url+"getByzip", zip, reservationList.getClass());
    }

    @Override
    public Reservation deactivateReservation(String id) {
        return restTemplate.postForObject(url+"deactivate", id, Reservation.class);
    }

    @Override
    public Reservation activateReservation(String id) {
        return restTemplate.postForObject(url+"activate", id, Reservation.class);
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
        return restTemplate.postForObject(url+"update", reservation, Reservation.class);
    }

    @Override
    public PageReservation getAllpage(Integer pageNo, Integer pageSize, String locationId) {
        PageReservation pageReservation=restTemplate.getForObject(url+"page?locationid="+locationId+"&pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id",PageReservation.class);
        return pageReservation;
    }
}
