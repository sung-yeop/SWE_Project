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
@Getter @Setter
public class Spot extends Coordinate{

    @Id
    @GeneratedValue
    @Column(name = "spot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mapPoint_id")
    private MapPoint spotPoint;

    public Spot init(MapPoint point){
        Spot spot = new Spot();
        StringTokenizer st = new StringTokenizer(point.getStr(), "( )");

        this.spotPoint = point;

        this.x = Integer.parseInt(st.nextToken());
        this.y = Integer.parseInt(st.nextToken());

        return spot;
    }
}
