package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.model.Menu;
import com.org.THC.service.LocationService;
import com.org.THC.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Menu> menuList;
    private LocationService locationService;
    String url="http://localhost:8081/menu/";

    @Autowired
    public DefaultMenuService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Menu> menuList,LocationService locationService){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.menuList = menuList;
        this.locationService=locationService;
    }

    @Override
    public boolean createMenus(String itemName, String description,  String price, String category, String locationId){
        Menu menu=new Menu();
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setCategory(category);
        menu.setLocation(locationService.getLocationsById(locationId));
        return restTemplate.postForObject(url+"create", menu, boolean.class);

    }

    @Override
    public List<Menu> getAllMenus(String id) {
        List<Menu> menusList= restTemplate.postForObject(url+"getAll",id, menuList.getClass());

        return menusList;
    }

    @Override
    public Menu getMenusById(String id) {
        return restTemplate.postForObject(url+"getById", id, Menu.class);
    }

    @Override
    public List<Menu> getMenusByZip(int zip) {
        return restTemplate.postForObject(url+"getByzip", zip, menuList.getClass());
    }

    @Override
    public Menu cancelMenu(String id) {
        return restTemplate.postForObject(url+"cancel", id, Menu.class);
    }

    @Override
    public Menu activeMenu(String id) {
        return restTemplate.postForObject(url+"active", id, Menu.class);
    }

    @Override
    public Menu updateMenu(String id,String itemName,String description,String price,String category,String status,String location) {
        Menu menu=new Menu();
        menu.setId(id);
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setCategory(category);
        menu.setStatus(status);
        return restTemplate.postForObject(url+"update", menu, Menu.class);
    }
}
