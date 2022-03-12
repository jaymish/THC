package com.org.THC.controller;

import com.org.THC.model.Location;
import com.org.THC.model.Menu;
import com.org.THC.service.LocationService;
import com.org.THC.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {

    private MenuService menuService;
    private LocationService locationService;



    @Autowired
    public MenuController(MenuService menuService, LocationService locationService){
        this.menuService = menuService;
        this.locationService=locationService;
    }

    @GetMapping("/addMenu")
    @ApiOperation(value = "Menu created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Created")
    })
    public String addWeatherReading(@ModelAttribute("location") String locationId,ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "menu/addMenu";
    }


    @PostMapping("/addMenu")
    @ApiOperation(value = "Menu created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Created")
    })
    public String addWeatherReading(@ModelAttribute("itemName") String itemName, @ModelAttribute("description") String description, @ModelAttribute("price") String price, @ModelAttribute("category") String category,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        menuService.createMenus(itemName, description, price, category,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/menu/getMenus?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/getMenus")
    @ApiOperation(value = "Get All menus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Menus Fetched")
    })
    public String getAll(@ModelAttribute("locationid") String id,ModelMap modelMap){
        List<Menu> menuList = menuService.getAllMenus(id);

        modelMap.put("LocationMenu",menuList);
        modelMap.put("locationid",id);
        return "menu/listMenu";
    }

    @PostMapping("/editMenu")
    @ApiOperation(value = "Get All menus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Menus Fetched")
    })
    public String editMenu(@ModelAttribute("id") String id,ModelMap modelMap){
        Menu menu= menuService.getMenusById(id);

        modelMap.put("Menus",menu);
        return "menu/editMenu";
    }

    @PostMapping("/updateMenu")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id")
    })
    public String updateMenu(@ModelAttribute("id") String id,@ModelAttribute("itemName") String itemName,@ModelAttribute("description") String description,@ModelAttribute("price") String price,@ModelAttribute("category") String category,@ModelAttribute("status") String status,@ModelAttribute("location") String location){
        menuService.updateMenu(id,itemName,description,price,category,status,location);
        return "redirect:/menu/getMenus?locationid="+location+"&deactivate=";
    }


    @PostMapping("/getMenuById")
    @ApiOperation(value = "Get menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Fetched by Id")
    })
    public Menu getById(@RequestBody String id){
        return menuService.getMenusById(id);
    }

    @PostMapping("/getMenuByZip")
    @ApiOperation(value = "Get menus by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Fetched by Zip")
    })
    public List<Menu> getMenusByZip(@RequestBody int zipcode){
        return menuService.getMenusByZip(zipcode);
    }

    @PostMapping("/cancelMenuById")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id")
    })
    public String cancelMenu(@ModelAttribute("id") String id){
        Menu menu= menuService.cancelMenu(id);
        return "redirect:/menu/getMenus?locationid="+menu.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activeMenuById")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id")
    })
    public String activeMenu(@ModelAttribute("id") String id){
        Menu menu= menuService.activeMenu(id);
        return "redirect:/menu/getMenus?locationid="+menu.getLocation().getId()+"&deactivate=";
    }
}
