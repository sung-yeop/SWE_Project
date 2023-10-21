package SWE_Project.backend.robot;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.movement.Direction;
import lombok.Getter;

@Getter
public class Robot {

    private Vector position;
    private Direction direction = Direction.UP;

    public void setPosition(Vector position) {
        this.position = position;
    }
}
