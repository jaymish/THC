package com.org.THC.repo.impl;

import com.org.THC.model.Menu;
import com.org.THC.repo.MenuRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MenuRepoImpl implements MenuRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Menu> getAllMenus(String locationid) {
        Query query = entityManager.createQuery("SELECT menus FROM Menu menus WHERE menus.location.id=:id").setParameter("id",locationid);
        List<Menu> menuList = (List<Menu>) query.getResultList();

        if(menuList.size() == 0 )
            return null;
        return menuList;
    }

    @Override
    public Menu getMenuById(String menuId) {
        Query query = entityManager.createQuery("SELECT menus FROM Menu menus WHERE menus.id = :menuId ")
                .setParameter("menuId",menuId);
        List<Menu> menuList = query.getResultList();
        System.out.println("here");
        System.out.println(menuList);
        if(menuList.size() == 0 )
            return null;
        return menuList.get(0);
    }



    @Override
    public Menu menuCancel(String id) {
        Menu menu = getMenuById(id);
        menu.setStatus("InActive");
        entityManager.merge(menu);
        return menu;
    }

    @Override
    public Menu menuActive(String id) {
        Menu menu = getMenuById(id);
        menu.setStatus("Active");
        entityManager.merge(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menuUpdated) {
        Menu menu = getMenuById(menuUpdated.getId());
        if (menu==null){
            return null;
        }
        menu.setItemName(menuUpdated.getItemName());
        menu.setDescription(menuUpdated.getDescription());
        menu.setPrice(menuUpdated.getPrice());
        menu.setCategory(menuUpdated.getCategory());
        entityManager.merge(menu);
        return menu;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        entityManager.persist(menu);
        return menu;
    }
}
