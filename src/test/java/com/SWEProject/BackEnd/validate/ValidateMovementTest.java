package com.SWEProject.BackEnd.validate;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

class ValidateMovementTest {
    private AddOn addOn;
    private Map map;

    // 지도 세팅
    @BeforeEach
    void setValidate() {
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

//    @Test
//    void Validate(){
//        Vector afterPosition = Vector.of(2, 12);
//        Vector beforeMovePosition = Vector.of(2, 10);
//        addOn.move();
//        while(validateMovement(map, afterPosition)){
//            addOn.setPosition(beforeMovePosition);
//            addOn.move();
//            afterPosition = addOn.getCurrentPosition();
//        }
//    }

}