package SWE_Project.backend.addon;

import SWE_Project.backend.map.Map;
import SWE_Project.backend.map.MapController;
import SWE_Project.backend.movement.Direction;
import SWE_Project.backend.movement.RobotMovementInterface;
import SWE_Project.backend.sensor.HazardSensor;
import SWE_Project.backend.sensor.SpotSensor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddOnTest {

    private RobotMovementInterface robotMovementInterface = new RobotMovementInterface();

    // 애초에 길찾기라는 것이 초기 정보는 모두 알고있어야 진행이 가능하므로 map데이터를 우선 AddOn에 가져온다.
    private MapController mapController = Map.getInstance();
    private HazardSensor hazardSensor = new HazardSensor();
    private SpotSensor spotSensor = new SpotSensor();

    @Test
    public void 로봇_방향_테스트() {
        //given
        Direction dir = robotMovementInterface.getRobot().getDirection();
        //when

        //then
        Assertions.assertThat(dir).isEqualTo(Direction.UP);
    }
}