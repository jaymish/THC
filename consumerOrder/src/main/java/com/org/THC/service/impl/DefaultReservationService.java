package com.org.THC.service.impl;

import com.org.THC.model.Reservation;
import com.org.THC.repo.ReservationRepo;
import com.org.THC.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private ReservationRepo reservationRepo;
    private EmailServiceImpl emailService;

    public DefaultReservationService(ReservationRepo reservationRepo, EmailServiceImpl emailService){
        this.reservationRepo = reservationRepo;
        this.emailService = emailService;
    }
    @Override
    public boolean createReservation(Reservation reservation) {
        reservationRepo.saveReservation(reservation);
        //emailService.sendSimpleMessage(reservations.getCustomer().getEmail_id(),"Reservation Created","Thank you your reservation was created. Here is the detail of your reservation \n"+ reservations.toString());
        return true;
    }

    @Override
    public List<Reservation> getAllReservations(String locationid) {
        List<Reservation> reservationList = reservationRepo.getAllReservations(locationid);

        return reservationList;
    }

    @Override
    public Reservation getReservationsById(String id){
        Reservation reservation = reservationRepo.getReservationById(id);
        return reservation;
    }


    @Override
    public Reservation cancelReservation(String id) {
        //logic to check if cancel is possible
        return reservationRepo.reservationCancel(id);
    }

    @Override
    public Reservation activeReservation(String id) {
        //logic to check if cancel is possible
        return reservationRepo.reservationActive(id);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepo.updateReservation(reservation);
    }
}
