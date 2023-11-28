package SWE_Project.backend.addon;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.map.Map;
import SWE_Project.backend.movement.RobotMovementInterface;
import SWE_Project.backend.sensor.SensorController;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddOn {

    private RobotMovementInterface robotMovementInterface = new RobotMovementInterface();
    private SensorController sensorController = new SensorController();

    //
    public List<Vector> pathFinding(Map map) {
        List<Vector> path = new ArrayList<>();

        AStar aStar = new AStar(map.getSize(), map.createMapInit());

        Vector current = sensorController.getPositioningSensor().getPosition();

        for (Vector spot : map.getSpotList()) {
            ArrayList<Vector> search = aStar.search(current, spot, map.getHazardList());
            for (Vector v : search) {
                if (!v.equals(spot)) {
                    path.add(v);
                }
            }
            current = spot;
        }
        path.add(current);

        return path;
    }

    // RobotMovementInterface를 이용하여 로봇을 움직이면서 센서를 작동하며 해당 경로가 맞는지 확인한다.
    // 이동 도중, 10% 확률로 2칸 이동 등의 오작동을 대비해야할 필요가 있다.
    // 추가적으로 숨겨진 스팟에 대한 내용도 한번에 처리하기 위해 매번 센서를 작동시킨다.
    // 경로를 인풋받아서 이동한다.
//    public Vector find(List<Vector> path) {
//        // path를 이용하여 경로 설정
//        // path에 나온 값과 현재 포지션의 값을 빼면서 방향 전환
//        // positioningSensor.updateDirection(방향);
//        // 전방이 HAZARD가 아닌지 확인
//        if (sensorController.sensor(robotMovementInterface.getRobot().getDirection(), controller.getMap().getHazardList(),
//                controller.getMap().getSpotList()) != SensorResult.HAZARD) {
//
//        }
//        // 앞으로 이동 (앞으로 이동한 후, 해당 포지션 값 반환 필요)
//        // 반환된 값과 위에서 확인한 path를 positioningSensor를 이용하여 정상 이동 확인
//        // Boolean flag = positioningSensor.updatePosition(위에서 반환된 값, path.get(0));
//        // 만약 정상이동이면 이동한 데이터를 Map -> MapController 저장 -> API -> Front로 데이터를 넘기면서 이동 화면에 표시
//        // <-> flag == flase이면 경로 재설정
//
//        return new Vector(0, 0);
//    }

    // 센서들의 작동을 묶어서 결과를 리턴하는 메서드

}
