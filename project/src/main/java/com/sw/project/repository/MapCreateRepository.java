package com.sw.project.repository;

import com.sw.project.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class MapCreateRepository {

    private final EntityManager em;

    public void saveMapCreate(MapPoint mapPoint) {
        MapCreate mapCreate = new MapCreate();
        mapCreate.init(mapPoint);
        em.persist(mapCreate);
    }

    public void saveStart(MapPoint mapPoint) {
        Start start = new Start();
        start.init(mapPoint);
        em.persist(start);
    }

    public void saveSpot(List<MapPoint> mapPoints) {
        for (MapPoint mapPoint : mapPoints) {
            Spot spot = new Spot();
            spot.init(mapPoint);
            em.persist(spot);
        }
    }

    public void saveHazard(List<MapPoint> mapPoints) {
        for (MapPoint mapPoint : mapPoints) {
            Hazard hazard = new Hazard();
            hazard.init(mapPoint);

            em.persist(hazard);
        }
    }

    public List<MapCreate> findAllMapCreate(){
        List<MapCreate> result = em.createQuery("select i from MapCreate i", MapCreate.class).getResultList();
        return result;
    }

    public List<Start> findAllStart(){
        List<Start> result = em.createQuery("select i from Start i", Start.class).getResultList();
        return result;
    }

    public List<Spot> findAllSpot(){
        List<Spot> result = em.createQuery("select i from Spot i", Spot.class).getResultList();
        return result;
    }

    public List<Hazard> findAllHazard(){
        List<Hazard> result = em.createQuery("select i from Hazard i", Hazard.class).getResultList();
        return result;
    }
}
