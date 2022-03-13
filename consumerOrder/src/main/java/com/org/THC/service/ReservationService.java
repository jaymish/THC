package com.org.THC.service;

import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;

import java.util.List;

public interface ReservationService {

    boolean createReservation(Reservation reservation);
    List<Reservation> getAllReservations(String locationid);
    Reservation getReservationsById(String id);
    Reservation deactivateReservation(String id);
    Reservation activateReservation(String id);
    Reservation updateReservation(Reservation reservation);
    PageReservation getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId);
}
