package com.SWEProject.BackEnd.sensor;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;

import java.util.List;

public class Sensor {
    private List<Vector> Hazards;
    private List<Vector> Colorblobs;

    public Sensor(List<Vector> hazards, List<Vector> colors) {
        Hazards = hazards;
        Colorblobs = colors;
//        // for Test
//        Hazards.add(Vector.of(7, 5));
//        Hazards.add(Vector.of(4, 4));
//        Hazards.add(Vector.of(5, 9));
//        Hazards.add(Vector.of(7, 8));
//        Colorblobs.add(Vector.of(2, 9));
        Hazards.add(Vector.of(2, 9));
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
        if (!Hazards.isEmpty()) {
            for (Vector hazard : Hazards) {
                if (compareVector.equals(hazard))
                    return hazard;
            }
        }
        return null;
    }

    public Vector getColorblobSensor(Vector position) {
        int x = position.x;
        int y = position.y;
        Vector compareVector = new Vector(x, y);

        if (!Colorblobs.isEmpty()) {
            compareVector.x = x;
            compareVector.y = y + 1; // 상
            for (Vector colorblob : Colorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x;
            compareVector.y = y - 1; // 하
            for (Vector colorblob : Colorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x - 1;
            compareVector.y = y; // 좌
            for (Vector colorblob : Colorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
            compareVector.x = x + 1;
            compareVector.y = y; // 우
            for (Vector colorblob : Colorblobs) {
                if (compareVector.equals(colorblob))
                    return colorblob;
            }
        }
        return null;

    }

    public boolean getPositioningSensor(Vector currentPosition, Vector intendedPosition) {
        return !currentPosition.equals(intendedPosition);
    }

    public void addHiddenHazard(Vector vector) {
        Hazards.add(vector);
    }

    public void addHiddenColor(Vector vector) {
        Colorblobs.add(vector);
    }
}