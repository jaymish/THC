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
    public List<Location> getAllLocations() {
        Query query = entityManager.createQuery("SELECT locations FROM Location locations");
        List<Location> locationList = (List<Location>) query.getResultList();
        return locationList;
    }

    @Override
    public Location getLocationById(String locationId) {
        Query query = entityManager.createQuery("SELECT locations FROM Location locations WHERE locations.id = :locationId ")
                .setParameter("locationId",locationId);
        List<Location> locationList = query.getResultList();
        if(locationList.size() == 0 )
            return null;
        return locationList.get(0);
    }

    @Override
    public List<Location> getLocationByZip(int zip) {
        Query query = entityManager.createQuery("SELECT locations FROM Location locations WHERE locations.deliveryMethod.shippingorpickup.zip = :zip ")
                .setParameter("zip",zip);
        List<Location> locationList = query.getResultList();
        if(locationList.size() == 0 )
            return null;
        return locationList;
    }

    @Override
    public Location locationCancel(String id) {
        Location location = getLocationById(id);
        location.setStatus("InActive");
        entityManager.merge(location);
        return location;
    }

    @Override
    public Location locationActive(String id) {
        Location location = getLocationById(id);
        location.setStatus("Active");
        entityManager.merge(location);
        return location;
    }

    @Override
    public Location updateLocation(Location locationUpdated) {
        Location location = getLocationById(locationUpdated.getId());
        if (location==null){
            return null;
        }
        location.setAddressline1(locationUpdated.getAddressline1());
        location.setAddressline2(locationUpdated.getAddressline2());
        location.setName(locationUpdated.getName());
        location.setCity(locationUpdated.getCity());
        location.setState(locationUpdated.getState());
        location.setZip(locationUpdated.getZip());
        entityManager.merge(location);
        return location;
    }

    @Override
    public Location saveLocation(Location location) {
        entityManager.persist(location);
        return location;
    }


}
