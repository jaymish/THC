package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.Menu;
import com.org.THC.model.PageMenu;
import com.org.THC.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    private MenuService menuService;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Menu received from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Saved for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean createMenus(@RequestBody Menu menu){
        logger.info("Controller:User trying to save menu "+menu.getItemName());
        menuService.createMenu(menu);
        return true;
    }


    @GetMapping(path = "/get-all")
    @Operation(summary = "Get All menus for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Menus Fetched for the location  for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<Menu> getMenus(@RequestParam("locationid") String locationid){
        List<Menu> menuList=menuService.getAllMenus(locationid);
        return menuList;
    }

    @PostMapping("/get-by-id")
    @Operation(summary = "Get menus by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Fetched by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Menu menusById(@RequestBody String id){
        return menuService.getMenusById(id);
    }


    @PostMapping("/deactivate")
    @Operation(summary = "Deactivate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<Menu> deactivateMenu(@RequestBody String id){
        logger.info("Controller:User trying to deactivate menu with id: "+id);
        return ResponseEntity.ok(menuService.deactivateMenu(id));
    }

    @PostMapping("/activate")
    @Operation(summary = "Activate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Menu activateMenu(@RequestBody String id){
        logger.info("Controller:User trying to activate menu with id: "+id);
        return menuService.activateMenu(id);
    }

    @PostMapping("/update")
    @Operation(summary = "Update Menu from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Menu updateMenu(@RequestBody Menu menu){
        logger.info("Controller:User trying to update and save menu with id: "+menu.getId());
        return menuService.updateMenu(menu);
    }

    @GetMapping("/page")
    @Operation(summary = "Get All Menu for the location page-vise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All menu for the location Fetched for client page-vise"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public PageMenu getAllMenu(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid,@RequestParam(defaultValue = "all") String show){
        return menuService.getAllpage(pageNo, pageSize, sortBy,locationid,show);
    }
}
