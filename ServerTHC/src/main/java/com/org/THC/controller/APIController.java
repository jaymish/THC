package com.org.THC.controller;

import com.org.THC.model.APIName;
import com.org.THC.model.PageAPIExecuation;
import com.org.THC.model.PageLocation;
import com.org.THC.service.APIExecutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class APIController {
    private APIExecutionService apiExecutionService;
    public APIController(APIExecutionService apiExecutionService){
        this.apiExecutionService=apiExecutionService;
    }

    @GetMapping("/page")
    @Operation(summary = "Get all api names page-vise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all apis Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public PageAPIExecuation getAllAPINames(@RequestParam(defaultValue = "all") String showbydate,@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "all") String showbyname){
        return apiExecutionService.getAllpage(pageNo, pageSize, sortBy,showbyname,showbydate);
    }

    @GetMapping("/list")
    @Operation(summary = "Get list of all api names ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get list of all apis Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<APIName> getAllNames(){
        return apiExecutionService.getAllNames();
    }
}
