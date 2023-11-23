package com.SWEProject.BackEnd.sim;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.movementsystem.MovementSystem;
import com.SWEProject.BackEnd.sensor.Sensor;

import java.util.Random;

// Sensor의 Hidden과 MovemnetSystem의 필드를 초기화할 수 있는 방법이 없음
// 동작 메커니즘 : AddOn의 pathFinding 기능 실행 -> Astar 알고리즘을 통해 경로 확보
// 이동하면서 Sensor를 돌려야하

public class Sim {
    private Sensor sensor;
    private MovementSystem movementSystem;


    public Sim(Vector startPoint) {
        sensor = new Sensor();
        movementSystem = new MovementSystem(startPoint);
    }

    public void directionSetting(Vector intendedPosition) {
        Vector currentPosition = movementSystem.getCurrentPosition();

        Vector update = Vector.of(intendedPosition.getX() - currentPosition.getX()
                , intendedPosition.getY() - currentPosition.getY());

        if(update.getX() == 1){ //오른쪽 이동
            while (!movementSystem.getDirection().equals(Direction.Right)) {
                movementSystem.turn();
            }
        }

        if(update.getX() == -1){ //왼쪽 이동
            while (!movementSystem.getDirection().equals(Direction.Left)) {
                movementSystem.turn();
            }
        }

        if(update.getY() == 1){
            while (!movementSystem.getDirection().equals(Direction.Up)) {
                movementSystem.turn();
            }
        }

        if (update.getY() == -1) {
            while (!movementSystem.getDirection().equals(Direction.Down)) {
                movementSystem.turn();
            }
        }
    }

    public void move(){
        movementSystem.move();
    }

    public Vector getPosition() {
        return movementSystem.getCurrentPosition();
    }

    //for Test Method
    public Direction getDirection() {
        return movementSystem.getDirection();
    }

    public Vector checkHazard() {
        Vector position = movementSystem.getCurrentPosition();
        Direction direction = movementSystem.getDirection();

        return sensor.getHazardSensor(position, direction);
    }

    public Vector checkColorblob() {
        Vector position = movementSystem.getCurrentPosition();

        return sensor.GetColorblobSensor(position);
    }

    public boolean checkPosition(Vector intendedPosition) {

        Vector currentPosition = movementSystem.getCurrentPosition();

        return sensor.GetPositioningSensor(currentPosition, intendedPosition);
    }

    public void setPosition(Vector beforeMovePosition) {
        movementSystem.setPosition(beforeMovePosition);
    }
}
