package SWE_Project.backend.robot;

import SWE_Project.backend.movement.Direction;
import lombok.Getter;

@Getter
public class Robot {

    // 로봇의 위치는 포지셔닝 센서가 확인해야하며, 로봇 객체는 별도의 포지션을 알 필요가 없음
    // 오직 방향 세팅만 하도록 설계
    private Direction direction = Direction.UP;

    public Direction setDirection(Direction direction) {
        this.direction = direction;
        return direction;
    }
}
