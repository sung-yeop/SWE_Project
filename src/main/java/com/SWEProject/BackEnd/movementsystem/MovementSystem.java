package com.SWEProject.BackEnd.movementsystem;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;

import java.util.Random;
import java.util.random.RandomGenerator;

public class MovementSystem {
    //for Test // start 포인트로 초기 설정
    private Vector currentPosition;
    private Direction direction;


    public MovementSystem(Vector currentPosition) {
        this.currentPosition = currentPosition;
        this.direction = Direction.Up; //초기 세팅 UP
    }

    public void move() {
        Random random = new Random();
        int percent = random.nextInt(1, 20);

//        if(percent == 1){ // 5% 확률로 2현재 Direction으로 2칸 이동
//            if(direction == Direction.Up){
//                currentPosition.y += 2;
//            }
//            if(direction == Direction.Right){
//                currentPosition.x += 2;
//            }
//            if(direction == Direction.Down){
//                currentPosition.y -= 2;
//            }
//            if (direction == Direction.Left) {
//                currentPosition.x -= 2;
//            }
//        }

        if(percent > 0 && percent <= 20){ //90% 확률로 정상 동작 //TODO: 현재 테스트를 위해 0, 20으로 설정 -> 추후 1, 19로 변경 필요
            if(direction == Direction.Up){
                currentPosition.y += 1;
            }
            if(direction == Direction.Right){
                currentPosition.x += 1;
            }
            if(direction == Direction.Down){
                currentPosition.y -= 1;
            }
            if (direction == Direction.Left) {
                currentPosition.x -= 1;
            }
        }
        // 나머지 5% 확률로 동작 X
    }

    public void turn(){ //시계 방향으로 회전하는 기능
        this.direction = Direction.getAfterDirectionWithNow(direction);
    }

    public Vector getCurrentPosition() {
        return currentPosition;
    }

    public Direction getDirection() {
        return direction;
    }


}

