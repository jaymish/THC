package com.org.THC.model;

import lombok.Data;

import java.util.List;

@Data
public class PageReservation {
    private List<Reservation> reservationList;
    private Integer pages;
}
