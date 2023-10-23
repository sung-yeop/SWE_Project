package SWE_Project.backend.map;

import SWE_Project.backend.common.Vector;
import java.util.List;
import lombok.Getter;

@Getter
public class Map {
    private static MapController mapController;

    // 초기 지도 생성
    public Map(Vector size, Vector startPoint, List<Vector> hazardList, List<Vector> spotList) {
        mapController.setSize(size);
        mapController.setStartSpot(startPoint);
        mapController.setHazardList(hazardList);
        mapController.setSpotList(spotList);
    }

    public static synchronized MapController getInstance() {
        if (mapController == null) {
            return mapController = new MapController();
        } else {
            return mapController;
        }
    }

    // 새로운 데이터는 어떻게 다룰것인지? // 새로운 리스트 생성?
    public void addHazard(Vector... hazard) {
        for (Vector vector : hazard) {
            mapController.getHazardList().add(vector);
        }
    }

    public void addSpot(Vector... Spot) {
        for (Vector vector : Spot) {
            mapController.getSpotList().add(vector);
        }
    }


}
