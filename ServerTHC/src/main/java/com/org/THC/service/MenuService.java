package com.org.THC.service;

import com.org.THC.model.Menu;
import com.org.THC.model.PageMenu;

import java.util.List;

public interface MenuService {
    boolean createMenu(Menu menu);
    List<Menu> getAllMenus(String locationid);
    Menu getMenusById(String id);
    Menu deactivateMenu(String id);
    Menu activateMenu(String id);
    Menu updateMenu(Menu menu);
    PageMenu getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId, String show);
}
