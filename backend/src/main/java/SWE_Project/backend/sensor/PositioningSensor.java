package SWE_Project.backend.sensor;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.movement.Direction;
import java.util.HashMap;
import java.util.Map;

// 내가 논리적으로 있어야할 위치와 현재 위치를 비교할 수 있어야함
// 업데이트하여 맵에 새로운 포지셔닝 위치를 제공해야함
public class PositioningSensor {

    // List.get(0) : 실제 위치 / List.get(1) : 논리적 위치
    private Map<String, Vector> position = new HashMap<>();
    private Direction direction;

    public PositioningSensor(Vector position, Direction dir) {
        this.position.put("real", position);
        this.position.put("logic", position);
        this.direction = dir;
    }

    public Vector getPosition() {
        return position.get("real");
    }

    //     현재 포지션 위치를 변경
    //     이동 후에는 항상 현재 위치가 정확한지 확인
    public Boolean updatePosition(Vector vector_real, Vector vector_logical) {

        return false;
    }

    public Direction updateDirection(Direction direction) {
        return this.direction = direction;
    }
}
