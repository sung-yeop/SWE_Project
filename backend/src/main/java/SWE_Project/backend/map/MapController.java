package SWE_Project.backend.map;

import SWE_Project.backend.common.Vector;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MapController {
    private Vector size;
    private Vector startSpot;
    private List<Vector> HazardList = new ArrayList<>();
    private List<Vector> SpotList = new ArrayList<>();
    private Vector position;

    public MapController(){
        if (position == null) {
            position = startSpot;
        }
    }
}