package com.sw.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@RequiredArgsConstructor
@Getter @Setter
@Slf4j
public class MapCreate extends Coordinate{

    @Id
    @GeneratedValue
    @Column(name = "mapCreate_id")
    private Long id;

    @OneToMany(mappedBy = "mapCreatePoint")
    private List<MapPoint> mapCreatePoint = new ArrayList<>();

    public MapCreate init(MapPoint point){
        MapCreate mapCreate = new MapCreate();

        StringTokenizer st = new StringTokenizer(point.getStr(), "( )");
        //연관관계
        mapCreate.mapCreatePoint.add(point);
        point.setMapCreatePoint(this);

        this.x = Integer.parseInt(st.nextToken());
        this.y = Integer.parseInt(st.nextToken());

        return mapCreate;
    }
}
