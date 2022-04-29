package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Reservation received from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Saved for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean createReservations(@RequestBody Reservation reservation){
        logger.info("Controller:User trying to save reservation "+reservation.getDate()+" "+reservation.getFirstName()+" "+reservation.getLastName()+" "+reservation.getPhoneNumber());
        reservationService.createReservation(reservation);
        return true;
    }


    @GetMapping(path = "/get-all")
    @Operation(summary = "Get All reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Reservation Fetched for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<Reservation> getReservations(@RequestParam("locationid") String locationid){
        List<Reservation> reservationList=reservationService.getAllReservations(locationid);
        return reservationList;
    }

    @PostMapping("/get-by-id")
    @Operation(summary = "Get reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Fetched by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Reservation reservationsById(@RequestBody String id){
        return reservationService.getReservationsById(id);
    }


    @PostMapping("/deactivate")
    @Operation(summary = "Deactivate reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<Reservation> deactivateReservation(@RequestBody String id){
        logger.info("Controller:User trying to deactivate reservation with id: "+id);
        return ResponseEntity.ok(reservationService.deactivateReservation(id));
    }

    @PostMapping("/activate")
    @Operation(summary = "Activate reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Reservation activateReservation(@RequestBody String id){
        logger.info("Controller:User trying to activate reservation with id: "+id);
        return reservationService.activateReservation(id);
    }

    @PostMapping("/update")
    @Operation(summary = "Update Reservation received from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Reservation updateReservation(@RequestBody Reservation reservation){
        logger.info("Controller:User trying to update and save reservation with id: "+reservation.getId());
        return reservationService.updateReservation(reservation);
    }

    @GetMapping("/page")
    @Operation(summary = "Get All Reservation for the location page-vise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Reservation for the location Fetched for client page-vise"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public PageReservation getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid){
        return reservationService.getAllpage(pageNo, pageSize, sortBy,locationid);
    }
}
