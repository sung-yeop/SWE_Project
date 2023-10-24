package SWE_Project.backend.addon;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.map.Map;
import SWE_Project.backend.map.MapController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AStarTest {
    private final MapController mapController = Map.getInstance();

    @Test
    public void 네이버_추가_테스트() {
        //given
        mapController.setSize(new Vector(3, 3));
        Vector[][] mapInit = mapController.createMapInit();

        //when
        Vector v = new Vector(0, 0);
        v.addNeighbors(mapController.getSize(), mapInit);

        //then
        ArrayList<Vector> neighbors = v.getNeighbors();
        for (Vector neighbor : neighbors) {
            System.out.println(neighbor.getX() + ", " + neighbor.getY());
        }
    }

    @Test
    public void 길찾기_로직_테스트() {
        //given
        mapController.setSize(new Vector(8, 11));
        mapController.setStartSpot(new Vector(3, 4));
        List<Vector> spotlist = new ArrayList<>();
        spotlist.add(new Vector(7, 8));
        mapController.setSpotList(spotlist);
        List<Vector> hazardlist = new ArrayList<>();
        hazardlist.add(new Vector(3, 5));
        hazardlist.add(new Vector(7, 4));
        mapController.setHazardList(hazardlist);


        AStar aStar = new AStar(mapController.getSize(), mapController.createMapInit());

        ArrayList<Vector> path = aStar.search(mapController.getStartSpot(), mapController.getSpotList().get(0)
                , mapController.getHazardList());
        for (Vector vector : path) {
            System.out.println("( " + vector.getX() + ", " + vector.getY() + " )");
        }
    }
}