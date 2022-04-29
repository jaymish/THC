package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.PageOpenHours;
import com.org.THC.model.PageReservation;
import com.org.THC.model.Reservation;
import com.org.THC.service.LocationService;
import com.org.THC.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(THCApplication.class);



    @Autowired
    public ReservationController(ReservationService reservationService, LocationService locationService){
        this.reservationService = reservationService;
        this.locationService=locationService;
    }

    @GetMapping("/add")
    @Operation(summary = "Add new Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add Reservation requested"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addReservation(@RequestParam("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "reservation/addReservation";
    }


    @PostMapping("/add")
    @Operation(summary = "Create Reservation received by user for given Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Saved"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addReservation(@ModelAttribute("date") String date,@ModelAttribute("time") String time,@ModelAttribute("firstName") String firstName,@ModelAttribute("lastName") String lastName,@ModelAttribute("phoneNumber") String phoneNumber,@ModelAttribute("emailId") String emailId,@ModelAttribute("status") String status,@ModelAttribute("noOfPeople") int noOfPeople,@ModelAttribute("location") String locationId,ModelMap modelMap) {
        logger.info("Controller:User trying to save reservation "+date+" "+firstName+" "+lastName+" "+phoneNumber);
        reservationService.createReservations(firstName, lastName, phoneNumber, emailId, noOfPeople, date, time ,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/reservation/get-all?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All Reservation for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Reservations Fetched for the location  for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String getAll(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize,@RequestParam("locationid") String id,ModelMap modelMap){
        PageReservation pageReservation=reservationService.getAllpage(pageNo,pageSize,id);
        modelMap.put("LocationReservation",pageReservation.getReservationList());
        if(pageReservation.getPages()<=0){
            modelMap.put("pages",0);
        }
        else {
            modelMap.put("pages", pageReservation.getPages() - 1);
        }
        modelMap.put("currentpage",pageNo);
        /*List<Reservation> reservationList = reservationService.getAllReservations(id);

        modelMap.put("LocationReservation",reservationList);*/
        modelMap.put("locationid",id);
        return "reservation/listReservation";
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit Reservation Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated reservation sent to save"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String editReservation(@ModelAttribute("id") String id,ModelMap modelMap){
        Reservation reservation= reservationService.getReservationsById(id);
        logger.info("Controller:User trying to edit reservation with id: "+id);
        modelMap.put("Reservations",reservation);
        return "reservation/editReservation";
    }

    @PostMapping("/update")
    @Operation(summary = "Update Reservation from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String updateReservation(@ModelAttribute("id") String id,@ModelAttribute("date") String date,@ModelAttribute("time") String time,@ModelAttribute("firstName") String firstName,@ModelAttribute("lastName") String lastName,@ModelAttribute("phoneNumber") String phoneNumber,@ModelAttribute("emailId") String emailId,@ModelAttribute("status") String status,@ModelAttribute("noOfPeople") int noOfPeople,@ModelAttribute("location") String location){
        logger.info("Controller:User trying to update and save reservation with id: "+id);
        reservationService.updateReservation(id,firstName, lastName, phoneNumber, emailId, noOfPeople, date, time , status, location);
        return "redirect:/reservation/get-all?locationid="+location+"&deactivate=";
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

    @PostMapping("/deactivate-by-id")
    @Operation(summary = "Deactivate Reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String deactivateReservation(@ModelAttribute("id") String id){
        logger.info("Controller:User trying to deactivate reservation with id: "+id);
        Reservation reservation= reservationService.deactivateReservation(id);
        return "redirect:/reservation/get-all?locationid="+reservation.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-by-id")
    @Operation(summary = "Activate Reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String activateReservation(@ModelAttribute("id") String id){
        logger.info("Controller:User trying to activate reservation with id: "+id);
        Reservation reservation= reservationService.activateReservation(id);
        return "redirect:/reservation/get-all?locationid="+reservation.getLocation().getId()+"&deactivate=";
    }
}
