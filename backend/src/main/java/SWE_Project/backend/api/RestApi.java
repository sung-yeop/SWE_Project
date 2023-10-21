package SWE_Project.backend.api;

import SWE_Project.backend.map.Map;
import SWE_Project.backend.map.MapController;
import lombok.Getter;

@Getter
public class RestApi {

    private MapController mapController = Map.getInstance();


}
