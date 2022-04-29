package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.service.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController implements ErrorController {

    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);

    @RequestMapping(path="/")
    @Operation(summary = "Main page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Main page opened successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String thcIndex(ModelMap modelMap){
        modelMap.put("message","");
        return "index";
    }

    @GetMapping(path = "/login")
    @Operation(summary = "Login for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String loginPage(){
        return "login";
    }

    @RequestMapping(path = "/error")
    @Operation(summary = "Error page")
    public String thcError(ModelMap modelMap) {
        modelMap.put("message", "Something went wrong. Please try again.");
        logger.error("Oops! Error found.");
        return "index";
    }

    @RequestMapping(path = "/registration")
    @Operation(summary = "New user Registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration successful"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String adminregistration(){
        return "registration";
    }

    @PostMapping(path = "/register")
    @Operation(summary = "User registered and returned to main page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration successful"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addUser(@ModelAttribute("name") String name,@ModelAttribute("password") String password,ModelMap modelMap){
        logger.info("Controller:User "+name+" trying to register");
        userDetailsService.saveUser(name,password);
        modelMap.put("message", "");
        return "index";
    }
}