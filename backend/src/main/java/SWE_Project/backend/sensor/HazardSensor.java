package SWE_Project.backend.sensor;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.movement.Direction;
import java.util.List;

public class HazardSensor {

    public boolean IsHazard(Vector position, Direction dir, List<Vector> hazardList) {
        Vector compareVector = new Vector(position.getX(), position.getY());
        switch (dir) {
            case UP:
                compareVector.y += 1;
                break;
            case DOWN:
                compareVector.y -= 1;
                break;
            case LEFT:
                compareVector.x -= 1;
                break;
            case RIGHT:
                compareVector.x += 1;
                break;
        }
        if (!hazardList.isEmpty()) {
            for (Vector hazard : hazardList) {
                if (compareVector.equals(hazard)) {
                    return true;
                }
            }
        }
        return false;
    }
}
