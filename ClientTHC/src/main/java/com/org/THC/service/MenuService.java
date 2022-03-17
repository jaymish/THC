package com.org.THC.service;

import com.org.THC.model.Menu;
import com.org.THC.model.PageMenu;

import java.util.List;


public interface MenuService {
    boolean createMenus(String itemName, String description,  String price,String locationId);
    List<Menu> getAllMenus(String id);
    Menu getMenusById(String id);
    List<Menu> getMenusByZip(int zip);
    Menu deactivateMenu(String id);
    Menu activateMenu(String id);
    Menu updateMenu(String id,String itemName,String description,String price,String status,String location);
    PageMenu getAllpage(Integer pageNo, Integer pageSize, String locationId,String show);
}
