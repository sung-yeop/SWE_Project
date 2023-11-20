package com.SWEProject.BackEnd.addOn;

import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.model.AStar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AddOnTest {

    private AddOn addOn;
    private Map map;

    // 지도 세팅
    @BeforeEach
    void setAddOn() {
        List<Vector> hazards = new ArrayList<>();
        List<Vector> spots = new ArrayList<>();
        List<Vector> colorBlobs = new ArrayList<>();

        hazards.add(Vector.of(3, 5));
        hazards.add(Vector.of(4, 5));
        hazards.add(Vector.of(6, 7));

        spots.add(Vector.of(3, 4));
        spots.add(Vector.of(7, 7));
        spots.add(Vector.of(8, 10));

        colorBlobs.add(Vector.of(10, 10));

        map = new Map(Vector.of(11, 11), Vector.of(2, 3), spots, hazards, colorBlobs);
        addOn = new AddOn(map.getStartPoint());
    }

    @DisplayName("이동 경로 생성 / Hidden 없는 경우")
    @Test
    void pathFinding_기능_테스트_Hidden_존재_X() {
        List<Vector> vectors = addOn.pathFind(map);
        for (Vector vector : vectors) {
            System.out.println(String.format("%d %d", vector.getX(), vector.getY()));
        }

//        Vector v = Vector.of(3, 5);
//        System.out.println(map.getHazardList().stream().anyMatch(m -> v.equals(m)));
    }

    @DisplayName("Astar 기능 테스트")
    @Test
    void Astar_기능_테스트() {
        AStar aStar = new AStar(map.getSize(), map.createMapInit());
        List<Vector> hazards = new ArrayList<>();

        hazards.add(Vector.of(3, 5));
        hazards.add(Vector.of(4, 5));
        hazards.add(Vector.of(6, 7));

        ArrayList<Vector> search = aStar.search(Vector.of(7, 7), Vector.of(8, 10), hazards);
        for (Vector vector : search) {
            System.out.println(String.format("%d %d", vector.getX(), vector.getY()));
        }
    }
}