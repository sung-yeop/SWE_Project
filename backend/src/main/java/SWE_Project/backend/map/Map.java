package SWE_Project.backend.map;

import SWE_Project.backend.common.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public class Map {
    private Vector size;
    private Vector startSpot;
    private List<Vector> hazardList = new ArrayList<>();
    private List<Vector> spotList = new ArrayList<>();

    // 초기 지도 생성

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

        Queue<Vector> mapqueue = createMap();

        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                result[j][i] = mapqueue.poll();
            }
        }

        return result;
    }

    // 새로운 데이터는 어떻게 다룰것인지? // 새로운 리스트 생성?
    public void addHazard(Vector... hazard) {
        for (Vector vector : hazard) {
            getHazardList().add(vector);
        }
    }

    public void addSpot(Vector... Spot) {
        for (Vector vector : Spot) {
            getSpotList().add(vector);
        }
    }


    public void init(Vector size, Vector startPoint, List<Vector> spotList, List<Vector> hazardList) {
        this.size = size;
        this.startSpot = startPoint;
        this.spotList = spotList;
        this.hazardList = hazardList;
    }
}
