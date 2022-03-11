package com.org.THC.service;

import com.org.THC.model.Menu;

import java.util.List;

public interface MenuService {
    boolean createMenus(Menu menu);
    List<Menu> getAllMenus(String id);
    Menu getMenusById(String id);
    List<Menu> getMenusByZip(int zip);
    Menu cancelMenu(String id);
    Menu activeMenu(String id);
    Menu updateMenu(Menu menu);
}
