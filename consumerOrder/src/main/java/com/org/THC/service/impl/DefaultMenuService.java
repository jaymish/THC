package com.org.THC.service.impl;

import com.org.THC.model.Menu;
import com.org.THC.repo.MenuRepo;
import com.org.THC.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {

    private MenuRepo menuRepo;
    private EmailServiceImpl emailService;

    public DefaultMenuService(MenuRepo menuRepo, EmailServiceImpl emailService){
        this.menuRepo = menuRepo;
        this.emailService = emailService;
    }
    @Override
    public boolean createMenu(Menu menu) {
        menuRepo.saveMenu(menu);
        //emailService.sendSimpleMessage(menus.getCustomer().getEmail_id(),"Menu Created","Thank you your menu was created. Here is the detail of your menu \n"+ menus.toString());
        return true;
    }

    @Override
    public List<Menu> getAllMenus(String locationid) {
        List<Menu> menuList = menuRepo.getAllMenus(locationid);

        return menuList;
    }

    @Override
    public Menu getMenusById(String id){
        Menu menu = menuRepo.getMenuById(id);
        return menu;
    }


    @Override
    public Menu cancelMenu(String id) {
        //logic to check if cancel is possible
        return menuRepo.menuCancel(id);
    }

    @Override
    public Menu activeMenu(String id) {
        //logic to check if cancel is possible
        return menuRepo.menuActive(id);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepo.updateMenu(menu);
    }
}
