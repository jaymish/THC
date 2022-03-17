package com.org.THC.controller;

import com.org.THC.model.Menu;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageMenu;
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


    @GetMapping(path = "/get-all")
    @ApiOperation(value = "Get All menus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Menus Fetched for client")
    })
    public List<Menu> getMenus(@RequestParam("locationid") String locationid){
        List<Menu> menuList=menuService.getAllMenus(locationid);
        return menuList;
    }

    @PostMapping("/get-by-id")
    @ApiOperation(value = "Get menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Fetched by Id for client" )
    })
    public Menu menusById(@RequestBody String id){
        return menuService.getMenusById(id);
    }


    @PostMapping("/deactivate")
    @ApiOperation(value = "Deactivate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivateed by Id for client")
    })
    public ResponseEntity<Menu> deactivateMenu(@RequestBody String id){
        return ResponseEntity.ok(menuService.deactivateMenu(id));
    }

    @PostMapping("/activate")
    @ApiOperation(value = "Deactivate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivateed by Id for client")
    })
    public Menu activateMenu(@RequestBody String id){
        return menuService.activateMenu(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Deactivate menus by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Menu Deactivateed by Id for client")
    })
    public Menu updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @GetMapping("/page")
    public PageMenu getAllMenu(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid,@RequestParam(defaultValue = "all") String show){
        return menuService.getAllpage(pageNo, pageSize, sortBy,locationid,show);
    }
}
