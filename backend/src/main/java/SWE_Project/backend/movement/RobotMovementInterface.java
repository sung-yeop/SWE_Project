package SWE_Project.backend.movement;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.robot.Robot;
import lombok.Getter;

@Getter
public class RobotMovementInterface {

    private Robot robot = new Robot();

    public RobotMovementInterface(Vector position){
        robot.setPosition(position);

    }


}
