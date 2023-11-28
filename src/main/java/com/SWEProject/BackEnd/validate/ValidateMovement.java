package com.SWEProject.BackEnd.validate;

import com.SWEProject.BackEnd.domain.Vector;

import java.util.List;

public class ValidateMovement {
    public static boolean validateMovement(Vector size, List<Vector> hazards, Vector afterCurrentPosition) {
        if (hazards.stream().anyMatch(vector -> vector.equals(afterCurrentPosition))) {
            return true;
        }
        if (afterCurrentPosition.getX() > size.getX() - 1
                || afterCurrentPosition.getY() > size.getY() - 1
                || afterCurrentPosition.getX() < 0 || afterCurrentPosition.getY() < 0) {
            return true;
        }
        return false;
    }
}
