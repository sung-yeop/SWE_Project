package SWE_Project.backend.map;

import SWE_Project.backend.common.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Vector 인스턴스를 생성하는 것은 오직 MapController에서 진행되도록 한다.

@Getter
@Setter
public class MapController {
    private Vector size;
    private Vector startSpot;
    private List<Vector> HazardList = new ArrayList<>();
    private List<Vector> SpotList = new ArrayList<>();
    private Vector position;

    public MapController() {
        if (position == null) {
            position = startSpot;
        }
    }

    public Queue<Vector> createMap() {
        Queue<Vector> result = new LinkedList<>();
        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                result.add(new Vector(j, i));
            }
        }
        return result;
    }

    public Vector[][] createMapInit() {
        Vector[][] result = new Vector[size.getX()][size.getY()];

        Queue<Vector> map = createMap();

        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                result[j][i] = map.poll();
            }
        }

        return result;
    }

    // 여기서 Astar 알고리즘에 사용할 맵 데이터를 생성해서 (네이버가 리스트로 연결되어있는 상태) 반환해줘야한다.
    // 파라미터는 해당 리스트에서 모두 사용이 가능하다.
}