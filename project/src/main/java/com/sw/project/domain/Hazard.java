package com.sw.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@RequiredArgsConstructor
@Getter @Slf4j
public class Hazard extends Coordinate{

    @Id
    @GeneratedValue
    @Column(name = "hazard_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mapPoint_id")
    private MapPoint hazardPoint;


    public Hazard init(MapPoint point){
        Hazard hazard = new Hazard();
        StringTokenizer st = new StringTokenizer(point.getStr(), "( )");

        this.hazardPoint = point;

        this.x = Integer.parseInt(st.nextToken());
        this.y = Integer.parseInt(st.nextToken());

        return hazard;
    }
}
