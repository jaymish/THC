package com.org.THC.controller;

import com.org.THC.model.Menu;
import com.org.THC.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    private MenuService menuService;
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Menu received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Saved on DB")
    })
    public boolean createMenus(@RequestBody Menu menu){
        menuService.createMenu(menu);
        return true;
    }


    @PostMapping(path = "/getAll")
    @ApiOperation(value = "Get All menus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Menus Fetched for client")
    })
    public List<Menu> getMenus(@RequestBody String locationid){
        List<Menu> menuList=menuService.getAllMenus(locationid);
        return menuList;
    }

    @PostMapping("/getById")
    @ApiOperation(value = "Get menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Fetched by Id for client" )
    })
    public Menu menusById(@RequestBody String id){
        return menuService.getMenusById(id);
    }


    @PostMapping("/cancel")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id for client")
    })
    public ResponseEntity<Menu> cancelMenu(@RequestBody String id){
        return ResponseEntity.ok(menuService.cancelMenu(id));
    }

    @PostMapping("/active")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id for client")
    })
    public Menu activeMenu(@RequestBody String id){
        return menuService.activeMenu(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Cancel menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Canceled by Id for client")
    })
    public Menu updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
}
