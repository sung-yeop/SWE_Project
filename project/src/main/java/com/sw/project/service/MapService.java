package com.sw.project.service;

import com.sw.project.domain.*;
import com.sw.project.repository.MapCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {

    private final MapCreateRepository mapCreateRepository;

    public void Init(String map, String start, String spot, String hazard) {


        InitMapCreate(map);
        InitStartPoint(start);
        InitSpotPoint(spot);
        InitHazardPoint(hazard);
    }

    public MapPoint InitMapCreate(String map){
        MapPoint mapPoint = MapPoint.createMapPoint(map);
        mapCreateRepository.saveMapCreate(mapPoint);

        return mapPoint;
    }

    public MapPoint InitStartPoint(String start) {
        MapPoint mapPoint = MapPoint.createMapPoint(start);
        mapCreateRepository.saveStart(mapPoint);

        return mapPoint;
    }

    public List<MapPoint> InitSpotPoint(String spot) {
        List<MapPoint> mapPoints = MapPoint.createMapPoints(spot);
        mapCreateRepository.saveSpot(mapPoints);

        return mapPoints;
    }

    public List<MapPoint> InitHazardPoint(String hazard) {
        List<MapPoint> mapPoints = MapPoint.createMapPoints(hazard);
        mapCreateRepository.saveHazard(mapPoints);

        return mapPoints;
    }

    public List<MapCreate> findAllMapCreate(){
        return mapCreateRepository.findAllMapCreate();
    }

    public List<Start> findAllStart(){
        return mapCreateRepository.findAllStart();
    }

    public List<Spot> findAllSpot(){
        return mapCreateRepository.findAllSpot();
    }

    public List<Hazard> findAllHazard() {
        return mapCreateRepository.findAllHazard();
    }

}
