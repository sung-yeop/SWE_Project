package SWE_Project.backend.sensor;

import SWE_Project.backend.common.Vector;

import java.util.List;

public class HazardSensor {

    public boolean IsHazard(Vector target, List<Vector> hazardList) {
        if (!hazardList.isEmpty()) {
            for (Vector hazard : hazardList) {
                if (target.equals(hazard)) {
                    return true;
                }
            }
        }
        return false;
    }
}
