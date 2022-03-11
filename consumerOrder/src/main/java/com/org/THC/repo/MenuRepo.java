package com.org.THC.repo;

import com.org.THC.model.Menu;

import java.util.List;

public interface MenuRepo {
    List<Menu> getAllMenus(String locationid);
    Menu getMenuById(String menuId);
    Menu saveMenu(Menu menu);
    Menu menuCancel(String id);
    Menu menuActive(String id);
    Menu updateMenu(Menu menu);
}
