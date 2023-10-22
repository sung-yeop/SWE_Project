package SWE_Project.backend.api;

import SWE_Project.backend.addon.AddOn;
import SWE_Project.backend.common.Vector;
import SWE_Project.backend.map.Map;
import SWE_Project.backend.map.MapController;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController @Slf4j
@RequiredArgsConstructor
public class RestApi {

    private MapController mapController = Map.getInstance();

    @PostMapping("api/mapInit")
    public response mapInit(@RequestBody @Validated createRequest request) {
        response result;
        Vector s = new Vector();

        List<Vector> size = s.extractVector(request.getSize());
        List<Vector> startPoint = s.extractVector(request.getStart());
        List<Vector> spotPoint = s.extractVector(request.getSpot());
        List<Vector> hazardPoint = s.extractVector(request.getHazard());

        result = new response(size, startPoint, spotPoint, hazardPoint);

        mapController.setSize(size.get(0));
        mapController.setStartSpot(startPoint.get(0));
        mapController.setSpotList(spotPoint);
        mapController.setHazardList(hazardPoint);

        return result;
    }

    @Data
    static class createRequest {
        private String size;
        private String start;
        private String spot;
        private String hazard;
    }


    @Data
    static class response{
        private List<Vector> size;
        private List<Vector> start;
        private List<Vector> spots;
        private List<Vector> hazards;

        public response(List<Vector> size, List<Vector> start, List<Vector> spots, List<Vector> hazards) {
            this.size = size;
            this.start = start;
            this.spots = spots;
            this.hazards = hazards;
        }
    }

}
