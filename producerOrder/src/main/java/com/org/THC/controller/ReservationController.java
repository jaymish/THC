package com.org.THC.controller;

import com.org.THC.model.Reservation;
import com.org.THC.service.LocationService;
import com.org.THC.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {


    private ReservationService reservationService;
    private LocationService locationService;



    @Autowired
    public ReservationController(ReservationService reservationService, LocationService locationService){
        this.reservationService = reservationService;
        this.locationService=locationService;
    }

    @GetMapping("/add-reservation")
    @ApiOperation(value = "Reservation created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Created")
    })
    public String addReservation(@ModelAttribute("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "reservation/addReservation";
    }


    @PostMapping("/add-reservation")
    @ApiOperation(value = "Reservation created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Created")
    })
    public String addReservation(@ModelAttribute("date") String date,@ModelAttribute("time") String time,@ModelAttribute("firstName") String firstName,@ModelAttribute("lastName") String lastName,@ModelAttribute("phoneNumber") String phoneNumber,@ModelAttribute("emailId") String emailId,@ModelAttribute("status") String status,@ModelAttribute("noOfPeople") int noOfPeople,@ModelAttribute("location") String locationId,ModelMap modelMap) {
        reservationService.createReservations(firstName, lastName, phoneNumber, emailId, noOfPeople, date, time ,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/reservation/get-reservation?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-reservation")
    @ApiOperation(value = "Get All reservations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Reservations Fetched")
    })
    public String getAll(@ModelAttribute("locationid") String id,ModelMap modelMap){
        List<Reservation> reservationList = reservationService.getAllReservations(id);

        modelMap.put("LocationReservation",reservationList);
        modelMap.put("locationid",id);
        return "reservation/listReservation";
    }

    @PostMapping("/edit-reservation")
    @ApiOperation(value = "Get All reservations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Reservations Fetched")
    })
    public String editReservation(@ModelAttribute("id") String id,ModelMap modelMap){
        Reservation reservation= reservationService.getReservationsById(id);

        modelMap.put("Reservations",reservation);
        return "reservation/editReservation";
    }

    @PostMapping("/update-reservation")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivated by Id")
    })
    public String updateReservation(@ModelAttribute("id") String id,@ModelAttribute("date") String date,@ModelAttribute("time") String time,@ModelAttribute("firstName") String firstName,@ModelAttribute("lastName") String lastName,@ModelAttribute("phoneNumber") String phoneNumber,@ModelAttribute("emailId") String emailId,@ModelAttribute("status") String status,@ModelAttribute("noOfPeople") int noOfPeople,@ModelAttribute("location") String location){
        reservationService.updateReservation(id,firstName, lastName, phoneNumber, emailId, noOfPeople, date, time , status, location);
        return "redirect:/reservation/get-reservation?locationid="+location+"&deactivate=";
    }


    /*@PostMapping("/getReservationById")
    @ApiOperation(value = "Get reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Fetched by Id")
    })
    public Reservation getById(@RequestBody String id){
        return reservationService.getReservationsById(id);
    }

    @PostMapping("/getReservationByZip")
    @ApiOperation(value = "Get reservations by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Fetched by Zip")
    })
    public List<Reservation> getReservationsByZip(@RequestBody int zipcode){
        return reservationService.getReservationsByZip(zipcode);
    }*/

    @PostMapping("/deactivate-reservation-by-id")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivated by Id")
    })
    public String deactivateReservation(@ModelAttribute("id") String id){
        Reservation reservation= reservationService.deactivateReservation(id);
        return "redirect:/reservation/get-reservation?locationid="+reservation.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-reservation-by-id")
    @ApiOperation(value = "Cancel reservations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation Deactivated by Id")
    })
    public String activateReservation(@ModelAttribute("id") String id){
        Reservation reservation= reservationService.activateReservation(id);
        return "redirect:/reservation/get-reservation?locationid="+reservation.getLocation().getId()+"&deactivate=";
    }
}
