package com.org.THC.repo;

import com.org.THC.model.Reservation;

import java.util.List;

public interface ReservationRepo {
    List<Reservation> getAllReservations(String locationid);
    Reservation getReservationById(String reservationId);
    Reservation saveReservation(Reservation reservation);
    Reservation reservationCancel(String id);
    Reservation reservationActive(String id);
    Reservation updateReservation(Reservation reservation);
}
