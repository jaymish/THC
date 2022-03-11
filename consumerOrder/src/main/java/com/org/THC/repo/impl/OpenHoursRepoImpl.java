package com.org.THC.repo.impl;

import com.org.THC.model.OpenHours;
import com.org.THC.repo.OpenHoursRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OpenHoursRepoImpl implements OpenHoursRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OpenHours> getAllOpenHours(String locationid) {
        Query query = entityManager.createQuery("SELECT openHourss FROM OpenHours openHourss WHERE openHourss.location.id=:id").setParameter("id",locationid);
        List<OpenHours> openHoursList = (List<OpenHours>) query.getResultList();

        if(openHoursList.size() == 0 )
            return null;
        return openHoursList;
    }

    @Override
    public OpenHours getOpenHoursById(String openHoursId) {
        Query query = entityManager.createQuery("SELECT openHourss FROM OpenHours openHourss WHERE openHourss.id = :openHoursId ")
                .setParameter("openHoursId",openHoursId);
        List<OpenHours> openHoursList = query.getResultList();
        System.out.println("here");
        System.out.println(openHoursList);
        if(openHoursList.size() == 0 )
            return null;
        return openHoursList.get(0);
    }



    @Override
    public OpenHours openHoursCancel(String id) {
        OpenHours openHours = getOpenHoursById(id);
        openHours.setStatus("InActive");
        entityManager.merge(openHours);
        return openHours;
    }

    @Override
    public OpenHours openHoursActive(String id) {
        OpenHours openHours = getOpenHoursById(id);
        openHours.setStatus("Active");
        entityManager.merge(openHours);
        return openHours;
    }

    @Override
    public OpenHours updateOpenHours(OpenHours openHoursUpdated) {
        OpenHours openHours = getOpenHoursById(openHoursUpdated.getId());
        if (openHours==null){
            return null;
        }
        openHours.setDay(openHoursUpdated.getDay());
        openHours.setStartTime(openHoursUpdated.getStartTime());
        openHours.setEndTime(openHoursUpdated.getEndTime());
        entityManager.merge(openHours);
        return openHours;
    }

    @Override
    public OpenHours saveOpenHours(OpenHours openHours) {
        entityManager.persist(openHours);
        return openHours;
    }
}
