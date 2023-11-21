package com.SWEProject.BackEnd.sensor;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private List<Vector> hiddenHazards;
    private List<Vector> hiddenColorblobs;


    public Sensor() {
        hiddenHazards = new ArrayList<Vector>();
        // for Test
        hiddenHazards.add(Vector.of(7, 5));
        hiddenHazards.add(Vector.of(4, 4));

        hiddenColorblobs = new ArrayList<Vector>();
    }

    public void addHazard(Vector position) {
        hiddenHazards.add(position);
    }

    public void addSpot(Vector position) {
        hiddenColorblobs.add(position);
    }

    public Vector getHazardSensor(Vector position, Direction dir) {
        Vector compareVector = new Vector(position.x, position.y);
        switch (dir) {
            case Up:
                compareVector.y += 1;
                break;
            case Down:
                compareVector.y -= 1;
                break;
            case Left:
                compareVector.x -= 1;
                break;
            case Right:
                compareVector.x += 1;
                break;
        }
        if (!hiddenHazards.isEmpty()) {
            for (Vector hazard : hiddenHazards) {
                if (compareVector.equals(hazard))
                    return hazard;
            }
        }
        return null;
    }

    public Vector GetColorblobSensor(Vector position) {
        int x = position.x;
        int y = position.y;
        Vector compareVector = new Vector(x, y);

        if (!hiddenColorblobs.isEmpty()) {
            compareVector.x = x;
            compareVector.y = y + 1; // 상
            for (Vector colorblob : hiddenColorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x;
            compareVector.y = y - 1; // 하
            for (Vector colorblob : hiddenColorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x - 1;
            compareVector.y = y; // 좌
            for (Vector colorblob : hiddenColorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x + 1;
            compareVector.y = y; // 우
            for (Vector colorblob : hiddenColorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
        }
        return null;

    }


    public boolean GetPositioningSensor(Vector currentPosition, Vector intendedPosition) {
        return !currentPosition.equals(intendedPosition);
    }
}