package SWE_Project.backend.movement;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.robot.Robot;
import lombok.Getter;

@Getter
public class RobotMovementInterface {

    private Robot robot = new Robot();

    // [직진 기능] 직진 이후 로봇이 위치할 벡터 반환
    // 해당 기능에는 10% 확률로 오작동 발생을 추가해야함
    public Vector go(Vector position, Vector target, Direction direction) {
        return new Vector(0, 0);
    }

    // 무조건 왼쪽으로만 회전
    public Direction turn() {

        // 보는 방향에 따라 회전하는 기능 추가 필요
        return robot.setDirection(Direction.RIGHT);
    }

}
