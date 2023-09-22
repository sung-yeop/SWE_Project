package com.sw.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@RequiredArgsConstructor
@Getter
public class Start extends Coordinate {

    @Id
    @GeneratedValue
    @Column(name = "start_id")
    private Long id;

    @OneToMany(mappedBy = "startPoint")
    private List<MapPoint> mapPoint = new ArrayList<>();

    public Start init(MapPoint point){
        Start start = new Start();
        StringTokenizer st = new StringTokenizer(point.getStr(), "( )");
        start.mapPoint.add(point);
        point.setStartPoint(this);

        this.x = Integer.parseInt(st.nextToken());
        this.y = Integer.parseInt(st.nextToken());

        return start;
    }
}
