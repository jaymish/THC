package com.org.THC.repo.impl;

import com.org.THC.model.Location;
import com.org.THC.repo.LocationRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LocationRepoImpl implements LocationRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Location> getAllOrders() {
        Query query = entityManager.createQuery("SELECT orders FROM Location orders");
        List<Location> locationList = (List<Location>) query.getResultList();
        System.out.println("here");
        System.out.println(locationList);
        return locationList;
    }

    @Override
    public Location getOrderById(String orderId) {
        Query query = entityManager.createQuery("SELECT orders FROM Location orders WHERE orders.id = :orderId ")
                .setParameter("orderId",orderId);
        List<Location> locationList = query.getResultList();
        System.out.println("here");
        System.out.println(locationList);
        if(locationList.size() == 0 )
            return null;
        return locationList.get(0);
    }

    @Override
    public List<Location> getOrderByZip(int zip) {
        Query query = entityManager.createQuery("SELECT orders FROM Location orders WHERE orders.deliveryMethod.shippingorpickup.zip = :zip ")
                .setParameter("zip",zip);
        List<Location> locationList = query.getResultList();
        System.out.println("here");
        System.out.println(locationList);
        if(locationList.size() == 0 )
            return null;
        return locationList;
    }

    @Override
    public Location orderCancel(String id) {
        Location location = getOrderById(id);
        location.setStatus("Cancel");
        entityManager.merge(location);
        return location;
    }

    @Override
    public Location saveOrder(Location location) {
        entityManager.persist(location);
        return location;
    }
}
