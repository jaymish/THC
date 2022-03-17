package com.org.THC.controller;

import com.org.THC.model.APIName;
import com.org.THC.model.PageAPIExecuation;
import com.org.THC.model.PageLocation;
import com.org.THC.service.APIService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class APIController {
    private APIService apiService;
    public APIController(APIService apiService){
        this.apiService=apiService;
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched")
    })
    public String getAll(@RequestParam(defaultValue = "all") String showbydate,@RequestParam(defaultValue = "all") String showbyname, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(defaultValue = "") String message, ModelMap modelMap){
        //List<Location> locationList = locationService.getAllLocations();
        List<APIName> apiNameList = apiService.getAllNames();
        PageAPIExecuation pageAPIExecuation=apiService.getAllpage(pageNo,pageSize,showbyname,showbydate);
        modelMap.put("apis",pageAPIExecuation.getApiExecutionTimeList());
        if(pageAPIExecuation.getPages()<=0){
            modelMap.put("pages",0);
        }
        else {
            modelMap.put("pages", pageAPIExecuation.getPages() - 1);
        }
        modelMap.put("showbydate",showbydate);
        modelMap.put("apiNameList",apiNameList);
        modelMap.put("currentpage",pageNo);
        modelMap.put("showbyname",showbyname);
        modelMap.put("message",message);
        return "api/listapi";//changeview
    }
}
