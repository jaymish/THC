package com.org.THC.controller;

import com.org.THC.model.User;
import com.org.THC.model.Users;
import com.org.THC.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Create Location received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Saved on DB")
    })
    public Users login(@RequestBody String username){
        Users user =userDetailsService.loadUserByUsername(username);
        return user;
        //return "true";
    }

    @PostMapping("/saveUser")
    @ApiOperation(value = "Create Location received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Saved on DB")
    })
    public String createUser(@RequestBody Users user){

        return userDetailsService.saveUser(user);
        //return "true";
    }
}
