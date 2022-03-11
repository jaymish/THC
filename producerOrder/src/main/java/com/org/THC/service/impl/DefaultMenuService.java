package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.model.Menu;
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
    String url="http://localhost:8081/menu/";

    @Autowired
    public DefaultMenuService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Menu> menuList){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.menuList = menuList;
    }

    @Override
    public boolean createMenus(Menu menu){
        System.out.println(menu);
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
    public Menu updateMenu(Menu menu) {
        return restTemplate.postForObject(url+"update", menu, Menu.class);
    }
}
