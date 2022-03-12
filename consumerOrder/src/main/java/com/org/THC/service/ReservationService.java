package com.org.THC.service;

import com.org.THC.model.Reservation;

import java.util.List;

public interface ReservationService {

    boolean createReservation(Reservation reservation);
    List<Reservation> getAllReservations(String locationid);
    Reservation getReservationsById(String id);
    Reservation cancelReservation(String id);
    Reservation activeReservation(String id);
    Reservation updateReservation(Reservation reservation);
}
