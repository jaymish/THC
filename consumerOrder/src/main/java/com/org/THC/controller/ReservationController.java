package com.org.THC.controller;

import com.org.THC.model.Reservation;
import com.org.THC.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Reservation received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Saved on DB")
    })
    public boolean createReservations(@RequestBody Reservation reservation){
        reservationService.createReservation(reservation);
        return true;
    }


    @PostMapping(path = "/getAll")
    @ApiOperation(value = "Get All reservations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Reservations Fetched for client")
    })
    public List<Reservation> getReservations(@RequestBody String locationid){
        List<Reservation> reservationList=reservationService.getAllReservations(locationid);
        return reservationList;
    }

    @PostMapping("/getById")
    @ApiOperation(value = "Get reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Fetched by Id for client" )
    })
    public Reservation reservationsById(@RequestBody String id){
        return reservationService.getReservationsById(id);
    }


    @PostMapping("/cancel")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Canceled by Id for client")
    })
    public ResponseEntity<Reservation> cancelReservation(@RequestBody String id){
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }

    @PostMapping("/active")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Canceled by Id for client")
    })
    public Reservation activeReservation(@RequestBody String id){
        return reservationService.activeReservation(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Canceled by Id for client")
    })
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }
}
