package com.org.THC.service.impl;

import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.repo.PageReservationRepo;
import com.org.THC.repo.ReservationRepo;
import com.org.THC.service.ReservationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private ReservationRepo reservationRepo;
    private EmailServiceImpl emailService;
    private PageReservationRepo pageReservationRepo;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);

    public DefaultReservationService(ReservationRepo reservationRepo, EmailServiceImpl emailService,PageReservationRepo pageReservationRepo){
        this.reservationRepo = reservationRepo;
        this.emailService = emailService;
        this.pageReservationRepo=pageReservationRepo;
    }
    @Override
    public boolean createReservation(Reservation reservation) {
        logger.info("Service:User trying to save reservation "+reservation.getDate()+" "+reservation.getFirstName()+" "+reservation.getLastName()+" "+reservation.getPhoneNumber());
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
    public Reservation deactivateReservation(String id) {
        //logic to check if deactivate is possible
        logger.info("Service:User trying to deactivate reservation with id: "+id);
        return reservationRepo.reservationDeactivate(id);
    }

    @Override
    public Reservation activateReservation(String id) {
        //logic to check if deactivate is possible
        logger.info("Service:User trying to activate reservation with id: "+id);
        return reservationRepo.reservationActivate(id);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        logger.info("Service:User trying to update and save reservation with id: "+reservation.getId());
        return reservationRepo.updateReservation(reservation);
    }

    @Override
    public PageReservation getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Reservation> pagedResult = pageReservationRepo.findByLocation_Id(locationId,paging);
        PageReservation pageReservation = new PageReservation();
        if(pagedResult.hasContent()) {
            pageReservation.setReservationList(pagedResult.getContent());
            pageReservation.setPages(pagedResult.getTotalPages());
            return pageReservation;
        } else {
            pageReservation.setReservationList(null);
            pageReservation.setPages(0);
            return pageReservation;
        }
    }
}
