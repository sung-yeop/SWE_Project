package SWE_Project.backend.movement;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.robot.Robot;
import lombok.Getter;

import java.util.Random;

@Getter
public class RobotMovementInterface {

    private Robot robot = new Robot();

    // [직진 기능] 직진 이후 로봇이 위치할 벡터 반환
    // 해당 기능에는 10% 확률로 오작동 발생을 추가해야함
    public Vector go(Vector position) {
        Direction direction = robot.getDirection();
        Random random = new Random();
        int rand = random.nextInt(19) + 1;
        Vector result_pos = null;

        if (rand < 5) { // 오작동 // 움직이지 않음 (5%)
            result_pos = position;

        } else if (rand >= 5 && rand < 10) { // 오작동 // 2칸 움직임 (5%)
            switch (direction) {
                case UP:
                    position.setY(position.getY() + 2);
                    break;
                case RIGHT:
                    position.setX(position.getX() + 2);
                    break;
                case DOWN:
                    position.setY(position.getY() - 2);
                    break;
                case LEFT:
                    position.setY(position.getX() - 2);
                    break;
            }
        } else { // 정상 작동
            switch (direction) {
                case UP:
                    position.setY(position.getY() + 1);
                    break;
                case RIGHT:
                    position.setX(position.getX() + 1);
                    break;
                case DOWN:
                    position.setY(position.getY() - 1);
                    break;
                case LEFT:
                    position.setY(position.getX() - 1);
                    break;
            }
        }

        return result_pos;
    }

    // 무조건 왼쪽으로만 회전
    public Direction turn() {
        Direction dir = null;
        switch (robot.getDirection()) {
            case UP:
                dir = robot.setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                dir = robot.setDirection(Direction.DOWN);
                break;
            case DOWN:
                dir = robot.setDirection(Direction.LEFT);
                break;
            case LEFT:
                dir = robot.setDirection(Direction.UP);
                break;
        }
        return dir;
    }
}
