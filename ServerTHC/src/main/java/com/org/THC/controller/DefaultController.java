package com.org.THC.controller;

import com.org.THC.model.User;
import com.org.THC.model.Users;
import com.org.THC.service.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class DefaultController {

    private UserDetailsService userDetailsService;
    public DefaultController(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }


    @PostMapping("/login")
    @Operation(summary = "Get user details for the given user name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got user details successfully if it exist or null if no user with the provided username"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Users login(@RequestBody String username){
        Users user =userDetailsService.loadUserByUsername(username);
        return user;
        //return "true";
    }

    @PostMapping("/saveUser")
    @Operation(summary = "Saved user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved user details successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String createUser(@RequestBody Users user){

        return userDetailsService.saveUser(user);
        //return "true";
    }
}
