package SWE_Project.backend.sensor;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.movement.Direction;
import lombok.Getter;

import java.util.List;

@Getter
public class SensorController {

    private HazardSensor hazardSensor = new HazardSensor();
    private SpotSensor spotSensor = new SpotSensor();
    private PositioningSensor positioningSensor = new PositioningSensor(Direction.UP);

    public void setPos(Vector vector) {
        Vector v = positioningSensor.updatePosition(vector);
    }


    public SensorResult sensor(Direction direction, List<Vector> hazards, List<Vector> spots) {
        Vector target = null;
        Vector position = positioningSensor.getPosition();

        switch (direction) {
            case UP -> target = new Vector(position.getX(), position.getY() + 1);
            case RIGHT -> target = new Vector(position.getX() + 1, position.getY());
            case DOWN -> target = new Vector(position.getX(), position.getY() - 1);
            case LEFT -> target = new Vector(position.getX() - 1, position.getY());
        }

        boolean hazard = hazardSensor.IsHazard(target, hazards);
        // boolean spot = spotSensor.IsSpot(target, spots);

        // spot 센서가 만들어지면 spot에 대한 판독 기능도 추가 필요

        if (hazard == true) {
            return SensorResult.HAZARD;
        } else {
            return SensorResult.SAFE;
        }
    }
}
