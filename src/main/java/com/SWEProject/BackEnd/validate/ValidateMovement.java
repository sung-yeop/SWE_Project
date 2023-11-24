package com.SWEProject.BackEnd.validate;

import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;

public class ValidateMovement {
    public static boolean validateMovement(Map map, Vector afterCurrentPosition) {
        if (map.getHazardList().stream().anyMatch(vector -> vector.equals(afterCurrentPosition))) {
            return true;
        }
        if (afterCurrentPosition.getX() > map.getSize().getX() - 1
                || afterCurrentPosition.getY() > map.getSize().getY() - 1
                || afterCurrentPosition.getX() < 0 || afterCurrentPosition.getY() < 0) {
            return true;
        }
        return false;
    }
}
