package com.org.THC.repo;

import com.org.THC.model.Menu;

import java.util.List;

public interface MenuRepo {
    List<Menu> getAllMenus(String locationid);
    Menu getMenuById(String menuId);
    Menu saveMenu(Menu menu);
    Menu menuDeactivate(String id);
    Menu menuActivate(String id);
    Menu updateMenu(Menu menu);
}
