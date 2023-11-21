package com.SWEProject.BackEnd.sim;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.movementsystem.MovementSystem;
import com.SWEProject.BackEnd.sensor.Sensor;

// Sensor의 Hidden과 MovemnetSystem의 필드를 초기화할 수 있는 방법이 없음
// 동작 메커니즘 : AddOn의 pathFinding 기능 실행 -> Astar 알고리즘을 통해 경로 확보
// 이동하면서 Sensor를 돌려야하

public class Sim {
    private Sensor sensor;
    private MovementSystem movementSystem;


    public Sim(Vector startPoint, Direction direction) {
        sensor = new Sensor();
        movementSystem = new MovementSystem(startPoint);
    }

    public void Move(Vector intendedPosition) {
        movementSystem.Move(intendedPosition);
    }

    //추가
    public void setDirection(Direction direction) {
        movementSystem.setDirection(direction);
    }

    public Vector getPosition() {
        return movementSystem.GetCurrentPosition();
    }

    public Vector CheckHazard() {
        Vector position = movementSystem.GetCurrentPosition();
        Direction direction = movementSystem.GetDirection();

        return sensor.GetHazardSensor(position, direction);
    }

    public Vector CheckColorblob() {
        Vector position = movementSystem.GetCurrentPosition();

        return sensor.GetColorblobSensor(position);
    }

    public boolean CheckPosition(Vector intendedPosition) {

        Vector currentPosition = movementSystem.GetCurrentPosition();

        return sensor.GetPositioningSensor(currentPosition, intendedPosition);
    }
}
