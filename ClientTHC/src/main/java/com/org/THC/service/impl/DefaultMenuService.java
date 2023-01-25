package com.org.THC.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.THCApplication;
import com.org.THC.model.Menu;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageMenu;
import com.org.THC.service.LocationService;
import com.org.THC.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Menu> menuList;
    private LocationService locationService;
    ////private static final Logger logger = LogManager.getLogger(THCApplication.class);
    @Value("${url.to.serverthc}")
    private String serverURL;

    String url="menu/";

    @Autowired
    public DefaultMenuService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Menu> menuList,LocationService locationService){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.menuList = menuList;
        this.locationService=locationService;
    }

    @Override
    public boolean createMenus(String itemName, String description,  String price, String locationId){
        Menu menu=new Menu();
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setLocation(locationService.getLocationsById(locationId));
        //logger.info("Service:User trying to save menu "+itemName+" using microservices");
        return restTemplate.postForObject(serverURL+url+"create", menu, boolean.class);

    }

    @Override
    public List<Menu> getAllMenus(String id) {
        List<Menu> menusList= restTemplate.getForObject(serverURL+url+"get-all?locationid="+id, menuList.getClass());

        return menusList;
    }

    @Override
    public Menu getMenusById(String id) {
        return restTemplate.postForObject(serverURL+url+"get-by-id", id, Menu.class);
    }

    @Override
    public List<Menu> getMenusByZip(int zip) {
        return restTemplate.postForObject(serverURL+url+"getByzip", zip, menuList.getClass());
    }

    @Override
    public Menu deactivateMenu(String id) {
        //logger.info("Service:User trying to deactivate menu with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"deactivate", id, Menu.class);
    }

    @Override
    public Menu activateMenu(String id) {
        //logger.info("Service:User trying to activate menu with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"activate", id, Menu.class);
    }

    @Override
    public Menu updateMenu(String id,String itemName,String description,String price,String status,String location) {
        Menu menu=new Menu();
        menu.setId(id);
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setStatus(status);
        //logger.info("Service:User trying to update and save menu with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"update", menu, Menu.class);
    }

    @Override
    public PageMenu getAllpage(Integer pageNo, Integer pageSize, String locationId,String show) {
        PageMenu pageMenu=restTemplate.getForObject(serverURL+url+"page?locationid="+locationId+"&pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id&show="+show,PageMenu.class);
        return pageMenu;
    }
}
