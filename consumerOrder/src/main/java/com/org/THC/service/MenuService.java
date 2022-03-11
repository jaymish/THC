package com.org.THC.service;

import com.org.THC.model.Menu;

import java.util.List;

public interface MenuService {
    boolean createMenu(Menu menu);
    List<Menu> getAllMenus(String locationid);
    Menu getMenusById(String id);
    Menu cancelMenu(String id);
    Menu activeMenu(String id);
    Menu updateMenu(Menu menu);
}
