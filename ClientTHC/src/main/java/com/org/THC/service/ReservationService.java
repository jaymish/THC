package com.org.THC.service;

import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;

import java.util.List;

public interface ReservationService {

    boolean createReservations(String firstName, String lastName,  String phoneNumber, String emailId,int noOfPeople,String date,String time , String locationId);
    List<Reservation> getAllReservations(String id);
    Reservation getReservationsById(String id);
    List<Reservation> getReservationsByZip(int zip);
    Reservation deactivateReservation(String id);
    Reservation activateReservation(String id);
    Reservation updateReservation(String id,String firstName, String lastName,  String phoneNumber, String emailId,int noOfPeople,String date,String time ,String status,String location);
    PageReservation getAllpage(Integer pageNo, Integer pageSize, String locationId);
}
