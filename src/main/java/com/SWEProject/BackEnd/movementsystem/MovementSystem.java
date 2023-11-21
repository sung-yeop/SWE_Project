package com.SWEProject.BackEnd.movementsystem;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;

public class MovementSystem {
    //for Test // start 포인트로 초기 설정
    private Vector currentPosition;
    private Direction direction;


    public MovementSystem(Vector currentPosition) {
        this.currentPosition = currentPosition;
        this.direction = Direction.Right; //초기 세팅 UP
    }

    public void move(Vector intendedPosition) {
        // TODO : 10퍼센트 확률로 오동작
        // TODO : 현재 방향과 다를경우 움직임 대신 회전동작
        currentPosition = intendedPosition;
    }

    public Vector getCurrentPosition() {
        return currentPosition;
    }

    public Direction getDirection() {
        return direction;
    }


}

