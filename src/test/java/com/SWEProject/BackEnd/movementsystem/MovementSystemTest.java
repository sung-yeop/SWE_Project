package com.SWEProject.BackEnd.movementsystem;

import com.SWEProject.BackEnd.domain.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementSystemTest {

    MovementSystem movementSystem;
    private String outputVectorFormat = "(%d, %d)";

    @BeforeEach
    void setMovementSystem(){
        movementSystem = new MovementSystem(Vector.of(2, 3)); //초기 위치는 (2, 3) | Direction은 UP
    }

    @Test
    void 이동_테스트(){
        movementSystem.move();
        Vector currentPosition1 = movementSystem.getCurrentPosition();
        String output1 = String.format(outputVectorFormat, currentPosition1.getX(), currentPosition1.getY());
        System.out.println(output1);

        movementSystem.move();
        Vector currentPosition2 = movementSystem.getCurrentPosition();
        String output2 = String.format(outputVectorFormat, currentPosition2.getX(), currentPosition2.getY());
        System.out.println(output2);

        movementSystem.move();
        Vector currentPosition3 = movementSystem.getCurrentPosition();
        String output3 = String.format(outputVectorFormat, currentPosition3.getX(), currentPosition3.getY());
        System.out.println(output3);



    }

}