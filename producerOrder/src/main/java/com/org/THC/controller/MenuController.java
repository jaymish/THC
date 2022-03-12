package com.org.THC.controller;

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

    @GetMapping("/add-menu")
    @ApiOperation(value = "Menu created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Created")
    })
    public String addMenu(@ModelAttribute("location") String locationId,ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "menu/addMenu";
    }


    @PostMapping("/add-menu")
    @ApiOperation(value = "Menu created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Created")
    })
    public String addMenu(@ModelAttribute("itemName") String itemName, @ModelAttribute("description") String description, @ModelAttribute("price") String price,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        menuService.createMenus(itemName, description, price,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/menu/get-menu?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-menu")
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

    @PostMapping("/edit-menu")
    @ApiOperation(value = "Get All menus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Menus Fetched")
    })
    public String editMenu(@ModelAttribute("id") String id,ModelMap modelMap){
        Menu menu= menuService.getMenusById(id);

        modelMap.put("Menus",menu);
        return "menu/editMenu";
    }

    @PostMapping("/update-menu")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivated by Id")
    })
    public String updateMenu(@ModelAttribute("id") String id,@ModelAttribute("itemName") String itemName,@ModelAttribute("description") String description,@ModelAttribute("price") String price,@ModelAttribute("status") String status,@ModelAttribute("location") String location){
        menuService.updateMenu(id,itemName,description,price,status,location);
        return "redirect:/menu/get-menu?locationid="+location+"&deactivate=";
    }


    /*@PostMapping("/getMenuById")
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
    }*/

    @PostMapping("/deactivate-menu-by-id")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivated by Id")
    })
    public String deactivateMenu(@ModelAttribute("id") String id){
        Menu menu= menuService.deactivateMenu(id);
        return "redirect:/menu/get-menu?locationid="+menu.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-menu-by-id")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivated by Id")
    })
    public String activateMenu(@ModelAttribute("id") String id){
        Menu menu= menuService.activateMenu(id);
        return "redirect:/menu/get-menu?locationid="+menu.getLocation().getId()+"&deactivate=";
    }
}
