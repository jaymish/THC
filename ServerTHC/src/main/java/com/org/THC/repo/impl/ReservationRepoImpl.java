package com.org.THC.repo.impl;

import com.org.THC.model.Reservation;
import com.org.THC.repo.ReservationRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReservationRepoImpl implements ReservationRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> getAllReservations(String locationid) {
        Query query = entityManager.createQuery("SELECT reservations FROM Reservation reservations WHERE reservations.location.id=:id").setParameter("id",locationid);
        List<Reservation> reservationList = (List<Reservation>) query.getResultList();

        if(reservationList.size() == 0 )
            return null;
        return reservationList;
    }

    @Override
    public Reservation getReservationById(String reservationId) {
        Query query = entityManager.createQuery("SELECT reservations FROM Reservation reservations WHERE reservations.id = :reservationId ")
                .setParameter("reservationId",reservationId);
        List<Reservation> reservationList = query.getResultList();
        if(reservationList.size() == 0 )
            return null;
        return reservationList.get(0);
    }



    @Override
    public Reservation reservationDeactivate(String id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus("InActive");
        entityManager.merge(reservation);
        return reservation;
    }

    @Override
    public Reservation reservationActivate(String id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus("Active");
        entityManager.merge(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(Reservation reservationUpdated) {
        Reservation reservation = getReservationById(reservationUpdated.getId());
        if (reservation==null){
            return null;
        }
        reservation.setFirstName(reservationUpdated.getFirstName());
        reservation.setLastName(reservationUpdated.getLastName());
        reservation.setPhoneNumber(reservationUpdated.getPhoneNumber());
        reservation.setEmailId(reservationUpdated.getEmailId());
        reservation.setNoOfPeople(reservationUpdated.getNoOfPeople());
        reservation.setDate(reservationUpdated.getDate());
        reservation.setTime(reservationUpdated.getTime());
        entityManager.merge(reservation);
        return reservation;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        entityManager.persist(reservation);
        return reservation;
    }
}
