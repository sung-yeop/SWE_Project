package SWE_Project.backend.addon;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.map.Map;
import SWE_Project.backend.map.MapController;
import SWE_Project.backend.movement.RobotMovementInterface;
import SWE_Project.backend.sensor.SensorController;
import SWE_Project.backend.sensor.SensorResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddOn {

    private RobotMovementInterface robotMovementInterface = new RobotMovementInterface();
    private MapController mapController = Map.getInstance();
    private SensorController sensorController = new SensorController(mapController.getPosition(), robotMovementInterface.getRobot().getDirection());


    // 위에서 제공된 mapController 객체를 통해 길찾기 로직을 진행하여 경로를 확인한다.
    public List<Vector> pathFinding() {
        List<Vector> path = new ArrayList<>();

        AStar aStar = new AStar(mapController.getSize(), mapController.createMapInit());

        for (Vector vector : mapController.getSpotList()) {
            ArrayList<Vector> search = aStar.search(sensorController.getPositioningSensor().getPosition(), vector,
                    mapController.getHazardList());
            for (Vector v : search) {
                path.add(v);
            }
        }

        // 미리 알고 있는 스팟들을 경유하면서 길을 찾는 알고리즘
        // 스팟 사이의 가중치를 만들고 가장 가까운 스팟 사이를 경유하는 과정을 만들어야 할 듯
        // 내부 메서드에서는 만약, 스팟을 찾아가는 과정에서 스팟을 밟고 지나간다면 해당 함수를 종료시키고 다시 스팟 찾는 알고리즘을 돌려야함

        return path;
    }

    // RobotMovementInterface를 이용하여 로봇을 움직이면서 센서를 작동하며 해당 경로가 맞는지 확인한다.
    // 이동 도중, 10% 확률로 2칸 이동 등의 오작동을 대비해야할 필요가 있다.
    // 추가적으로 숨겨진 스팟에 대한 내용도 한번에 처리하기 위해 매번 센서를 작동시킨다.
    // 경로를 인풋받아서 이동한다.
    public Vector find(List<Vector> path) {
        // path를 이용하여 경로 설정
        // path에 나온 값과 현재 포지션의 값을 빼면서 방향 전환
        // positioningSensor.updateDirection(방향);
        // 전방이 HAZARD가 아닌지 확인
        if (sensorController.sensor(robotMovementInterface.getRobot().getDirection(), mapController.getHazardList(),
                mapController.getSpotList()) != SensorResult.HAZARD) {

        }
        // 앞으로 이동 (앞으로 이동한 후, 해당 포지션 값 반환 필요)
        // 반환된 값과 위에서 확인한 path를 positioningSensor를 이용하여 정상 이동 확인
        // Boolean flag = positioningSensor.updatePosition(위에서 반환된 값, path.get(0));
        // 만약 정상이동이면 이동한 데이터를 Map -> MapController 저장 -> API -> Front로 데이터를 넘기면서 이동 화면에 표시
        // <-> flag == flase이면 경로 재설정

        return new Vector(0, 0);
    }

    // 센서들의 작동을 묶어서 결과를 리턴하는 메서드

}
