package com.sw.project.apicontroller;

import com.sw.project.domain.*;
import com.sw.project.repository.MapCreateRepository;
import com.sw.project.service.MapService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapApiController {

    private final MapService mapService;

    @PostMapping("/api/mapInit")
    public response mapInit(@RequestBody @Validated createMapRequest request){

        MapCreate mapCreate = mapService.InitMapCreate(request.getMap()).getMapCreatePoint();

        Start start = mapService.InitStartPoint(request.getStart()).getStartPoint();

        List<MapPoint> spot = mapService.InitSpotPoint(request.getSpot());
        List<Spot> allSpot = mapService.findAllSpot();

        List<MapPoint> hazard = mapService.InitHazardPoint(request.getHazard());
        List<Hazard> allHazard = mapService.findAllHazard();

        MapPoint mapPoint = MapPoint.create(mapCreate, start, allSpot, allHazard);

        return new response(mapPoint);


    }

    @Data
    static class response{
        private MapCreateDto map;
        private StartDto start;
        private List<SpotDto> spots;
        private List<HazardDto> hazards;

        public response(MapPoint mapPoint) {
            this.map = new MapCreateDto(mapPoint.getMapCreatePoint().getX(), mapPoint.getMapCreatePoint().getY());
            this.start = new StartDto(mapPoint.getStartPoint().getX(), mapPoint.getStartPoint().getY());
            this.spots = mapPoint.getSpotPoint().stream().map(o -> new SpotDto(o)).collect(Collectors.toList());
            this.hazards = mapPoint.getHazardPoint().stream().map(o -> new HazardDto(o)).collect(Collectors.toList());
        }
    }

    @Getter
    static class HazardDto {
        private int x;
        private int y;

        public HazardDto(Hazard hazard) {
            this.x = hazard.getX();
            this.y = hazard.getY();
        }
    }


    @Getter
    static class SpotDto {
        private int x;
        private int y;

        public SpotDto(Spot spot) {
            this.x = spot.getX();
            this.y = spot.getY();
        }
    }


    @Getter
    static class StartDto {
        private int x;
        private int y;

        public StartDto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    @Getter
    static class MapCreateDto {
        private int x;
        private int y;

        public MapCreateDto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Data
    static class createMapRequest {
        private String Map;
        private String Start;
        private String Spot;
        private String Hazard;
    }
}
