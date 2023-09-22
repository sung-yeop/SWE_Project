package com.sw.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.persistence.*;
import java.util.*;

@Entity
@RequiredArgsConstructor
@Getter @Setter @Slf4j
//지도 탐색을 진행할 때, 위험 지역과 spot 등의 모든 데이터를 확인하기 위하여 각 엔티티를 연결할 수 있는 엔티티이다.
public class MapPoint {

    @Id
    @GeneratedValue
    @Column(name = "map_point_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mapCreate_id")
    private MapCreate mapCreatePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_id")
    private Start startPoint;

    @OneToMany(mappedBy = "spotPoint")
    @JsonIgnore
    private List<Spot> spotPoint = new ArrayList<>();

    @OneToMany(mappedBy = "hazardPoint")
    @JsonIgnore
    private List<Hazard> hazardPoint = new ArrayList<>();

    private String str;

    public static MapPoint create(MapCreate mapCreate, Start start, List<Spot> spot, List<Hazard> hazard){
        MapPoint mapPoint = new MapPoint();
        mapPoint.mapCreatePoint = mapCreate;
        mapPoint.startPoint = start;
        mapPoint.spotPoint = spot;
        mapPoint.hazardPoint = hazard;

        return mapPoint;
    }

    public static MapPoint createMapPoint(String str){
        MapPoint mapPoint = new MapPoint();
        mapPoint.setStr(str);
        return mapPoint;
    }

    public static List<MapPoint> createMapPoints(String str) {
        List<MapPoint> mapPoints = new ArrayList<>();

        String[] strs = str.split("\\)");
        for(int i=0; i<strs.length; i++){
            MapPoint point = new MapPoint();
            point.setStr(strs[i]);
            mapPoints.add(point);
        }

        return mapPoints;
    }
}
