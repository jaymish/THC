package com.org.THC.controller;

import com.org.THC.model.PageLocation;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(path = "/get-all")
    @ApiOperation(value = "Get All reservations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Reservations Fetched for client")
    })
    public List<Reservation> getReservations(@RequestParam("locationid") String locationid){
        List<Reservation> reservationList=reservationService.getAllReservations(locationid);
        return reservationList;
    }

    @PostMapping("/get-by-id")
    @ApiOperation(value = "Get reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Fetched by Id for client" )
    })
    public Reservation reservationsById(@RequestBody String id){
        return reservationService.getReservationsById(id);
    }


    @PostMapping("/deactivate")
    @ApiOperation(value = "Deactivate reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivateed by Id for client")
    })
    public ResponseEntity<Reservation> deactivateReservation(@RequestBody String id){
        return ResponseEntity.ok(reservationService.deactivateReservation(id));
    }

    @PostMapping("/activate")
    @ApiOperation(value = "Deactivate reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivateed by Id for client")
    })
    public Reservation activateReservation(@RequestBody String id){
        return reservationService.activateReservation(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Deactivate reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivateed by Id for client")
    })
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }

    @GetMapping("/page")
    public PageReservation getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid){
        return reservationService.getAllpage(pageNo, pageSize, sortBy,locationid);
    }
}
