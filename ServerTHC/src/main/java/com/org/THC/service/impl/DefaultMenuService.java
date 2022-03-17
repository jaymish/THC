package com.org.THC.service.impl;

import com.org.THC.model.Location;
import com.org.THC.model.Menu;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageMenu;
import com.org.THC.repo.MenuRepo;
import com.org.THC.repo.PageMenuRepo;
import com.org.THC.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {

    private MenuRepo menuRepo;
    private EmailServiceImpl emailService;
    private PageMenuRepo pageMenuRepo;

    public DefaultMenuService(MenuRepo menuRepo, EmailServiceImpl emailService,PageMenuRepo pageMenuRepo){
        this.menuRepo = menuRepo;
        this.emailService = emailService;
        this.pageMenuRepo=pageMenuRepo;
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
    public Menu deactivateMenu(String id) {
        //logic to check if deactivate is possible
        return menuRepo.menuDeactivate(id);
    }

    @Override
    public Menu activateMenu(String id) {
        //logic to check if deactivate is possible
        return menuRepo.menuActivate(id);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepo.updateMenu(menu);
    }

    @Override
    public PageMenu getAllpage(Integer pageNo, Integer pageSize, String sortBy, String locationId, String show){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Menu> pagedResult; pageMenuRepo.findMenuByLocation_Id(locationId,paging);
        if(show.equals("all")) {
            pagedResult = pageMenuRepo.findMenuByLocation_Id(locationId,paging);
        }
        else{
            pagedResult = pageMenuRepo.findByStatusAndLocation_Id(show,locationId,paging);
        }
        PageMenu pageMenu = new PageMenu();
        if(pagedResult.hasContent()) {
            pageMenu.setMenuList(pagedResult.getContent());
            pageMenu.setPages(pagedResult.getTotalPages());
            return pageMenu;
        } else {
            pageMenu.setMenuList(null);
            pageMenu.setPages(0);
            return pageMenu;
        }
    }
}
