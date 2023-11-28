package SWE_Project.backend.controller;

import SWE_Project.backend.addon.AddOn;
import SWE_Project.backend.common.Vector;
import SWE_Project.backend.map.Map;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Controller {
    private Map map = new Map();
    private AddOn addOn = new AddOn();

    public void mapInit(Vector size, Vector startPoint, List<Vector> spotList, List<Vector> hazardList) {
        map.init(size, startPoint, spotList, hazardList);
    }

    public List<Vector> pathFinding(Map map) {
        return addOn.pathFinding(map);
    }


}
