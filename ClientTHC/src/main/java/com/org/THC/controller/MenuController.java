package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.Menu;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageMenu;
import com.org.THC.service.LocationService;
import com.org.THC.service.MenuService;
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
@RequestMapping(path = "/menu")
public class MenuController {

    private MenuService menuService;
    private LocationService locationService;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);



    @Autowired
    public MenuController(MenuService menuService, LocationService locationService){
        this.menuService = menuService;
        this.locationService=locationService;
    }

    @GetMapping("/add")
    @Operation(summary = "Add new Menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add Menu requested"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addMenu(@RequestParam("location") String locationId,ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "menu/addMenu";
    }


    @PostMapping("/add")
    @Operation(summary = "Create Menu received by user for given Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Saved"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addMenu(@ModelAttribute("itemName") String itemName, @ModelAttribute("description") String description, @ModelAttribute("price") String price,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        logger.info("Controller:User trying to save menu "+itemName);
        menuService.createMenus(itemName, description, price,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/menu/get-all?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All menus for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Menus Fetched for the location  for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String getAll(@RequestParam(defaultValue = "all") String show,@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize,@RequestParam("locationid") String id,ModelMap modelMap){
        PageMenu pageMenu=menuService.getAllpage(pageNo,pageSize,id,show);
        modelMap.put("LocationMenu",pageMenu.getMenuList());
        if(pageMenu.getPages()<=0){
            modelMap.put("pages",0);
        }
        else {
            modelMap.put("pages", pageMenu.getPages() - 1);
        }
        modelMap.put("currentpage",pageNo);
        modelMap.put("show",show);


       /* List<Menu> menuList = menuService.getAllMenus(id);

        modelMap.put("LocationMenu",menuList);*/
        modelMap.put("locationid",id);
        return "menu/listMenu";
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit Menu Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated menu sent to save"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String editMenu(@ModelAttribute("id") String id,ModelMap modelMap){
        Menu menu= menuService.getMenusById(id);
        logger.info("Controller:User trying to edit menu with id: "+id);
        modelMap.put("Menus",menu);
        return "menu/editMenu";
    }

    @PostMapping("/update")
    @Operation(summary = "Update Menu from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String updateMenu(@ModelAttribute("id") String id,@ModelAttribute("itemName") String itemName,@ModelAttribute("description") String description,@ModelAttribute("price") String price,@ModelAttribute("status") String status,@ModelAttribute("location") String location){
        logger.info("Controller:User trying to update and save menu with id: "+id);
        menuService.updateMenu(id,itemName,description,price,status,location);
        return "redirect:/menu/get-all?locationid="+location+"&deactivate=";
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

    @PostMapping("/deactivate-by-id")
    @Operation(summary = "Deactivate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String deactivateMenu(@ModelAttribute("id") String id,@ModelAttribute("show") String show){
        logger.info("Controller:User trying to deactivate menu with id: "+id);
        Menu menu= menuService.deactivateMenu(id);
        return "redirect:/menu/get-all?show="+show+"&locationid="+menu.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-by-id")
    @Operation(summary = "Activate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String activateMenu(@ModelAttribute("id") String id,@ModelAttribute("show") String show){
        logger.info("Controller:User trying to activate menu with id: "+id);
        Menu menu= menuService.activateMenu(id);
        return "redirect:/menu/get-all?show="+show+"&locationid="+menu.getLocation().getId()+"&deactivate=";
    }
}
