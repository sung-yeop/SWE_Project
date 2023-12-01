package com.SWEProject.BackEnd.sim;

import com.SWEProject.BackEnd.addOn.Vector;

import java.util.List;

public class Sim {
    private Sensor sensor;
    private MovementSystem movementSystem;


    public Sim(Vector startPoint, List<Vector> preHazards, List<Vector> preColors) {
        sensor = new Sensor(preHazards, preColors);
        movementSystem = new MovementSystem(startPoint);
    }

    public void directionSetting(Vector intendedPosition) {
        Vector currentPosition = movementSystem.getCurrentPosition();

        Vector update = Vector.of(intendedPosition.getX() - currentPosition.getX()
                , intendedPosition.getY() - currentPosition.getY());

        if (update.getX() == 1) { //오른쪽 이동
            while (!movementSystem.getDirection().equals(Direction.Right)) {
                movementSystem.turn();
            }
        }

        if (update.getX() == -1) { //왼쪽 이동
            while (!movementSystem.getDirection().equals(Direction.Left)) {
                movementSystem.turn();
            }
        }

        if (update.getY() == 1) {
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

    public void move() {
        movementSystem.move();
    }

    public Vector getPosition() {
        return movementSystem.getCurrentPosition();
    }

    public Direction getDirection() {
        return movementSystem.getDirection();
    }

    public void setPosition(Vector beforeMovePosition) {
        movementSystem.setPosition(beforeMovePosition);
    }

    public Vector checkHazard() {
        Vector position = movementSystem.getCurrentPosition();
        Direction direction = movementSystem.getDirection();

        return sensor.getHazardSensor(position, direction);
    }

    public List<Vector> checkColorblob() {
        Vector position = movementSystem.getCurrentPosition();

        return sensor.getColorblobSensor(position);
    }

    public boolean checkPosition(Vector intendedPosition) {
        Vector currentPosition = movementSystem.getCurrentPosition();

        return sensor.getPositioningSensor(currentPosition, intendedPosition);
    }

    public void addHiddenHazard(Vector vector) {
        sensor.addHiddenHazard(vector);
    }

    public void addHiddenColor(Vector vector) {
        sensor.addHiddenColor(vector);
    }
}
