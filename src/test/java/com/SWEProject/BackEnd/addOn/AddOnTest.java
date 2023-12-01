package com.SWEProject.BackEnd.addOn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.SWEProject.BackEnd.addOn.Converter.convertVectorToString;

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

        map = new Map(Vector.of(11, 11), Vector.of(2, 3), hazards, spots, colorBlobs);
        addOn = new AddOn(map.getStartPoint(), map.getHazardList(), map.getColorblobList());
    }

    @DisplayName("이동 경로 중 문제 발생 / 오작동 없는 경우")
    @Test
    void pathFinding_기능_테스트_Hidden_존재_O() {
        List<Vector> initPath = addOn.pathFind(map.getSize(), map.getMapInit(), map.getSpotList());
        int cnt = 0;
        while (cnt < 100) {
            for (Vector vector : initPath) {

                if (vector.equals(map.getSpotList().get(map.getSpotList().size() - 1))) {
                    return;
                }

                boolean moveFlag = true;
                addOn.directionSetting(vector);
                System.out.println(String.format("현재 위치 : (%d, %d)",
                        addOn.getCurrentPosition().getX(), addOn.getCurrentPosition().getY()));
                System.out.println(String.format("목표 위치 : (%d, %d)\n============", vector.getX(), vector.getY()));

                if (addOn.moveWithHazardSense()) {
                    map.getHazardList().add(vector);
                    if (map.getHazardList().stream().anyMatch(v -> v.equals(vector))) {
                        initPath = addOn.pathFind(map.getSize(), map.getMapInit(), map.getSpotList());
                        String outPath = initPath.stream()
                                .map(v -> convertVectorToString(v)).collect(Collectors.joining("\n"));
                        System.out.println(outPath);
                    }
                    moveFlag = false;
                    break;
                }

                if (addOn.getCurrentPosition().equals(vector)) {
                    moveFlag = false;
                } // 현재 위치와 목표 위치가 동일하다면 작동 X

                if (moveFlag) {
                    addOn.move();
                }

                if (map.getSpotList().stream().anyMatch(v -> v.equals(addOn.getCurrentPosition()))) { //탐지했으면 찾아야할 Spotlist에서 삭제
                    addOn.setCheckSpot(addOn.getCurrentPosition());
                }

            }
            cnt++; //총 100번 동작
        }


    }

    @DisplayName("이동 경로 생성 / Hidden 없는 경우")
    @Test
    void pathFinding_기능_테스트_Hidden_존재_X() {
        List<Vector> vectors = addOn.pathFind(map.getSize(), map.getMapInit(), map.getSpotList());
        for (Vector vector : vectors) {
            System.out.println(String.format("%d %d", vector.getX(), vector.getY()));
        }

//        Vector v = Vector.of(3, 5);
//        System.out.println(map.getHazardList().stream().anyMatch(m -> v.equals(m)));
    }

    @DisplayName("Astar 기능 테스트")
    @Test
    void Astar_기능_테스트() {
        AStar aStar = new AStar(map.getSize(), map.getMapInit());
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