package SWE_Project.backend.api;

import SWE_Project.backend.common.Vector;
import SWE_Project.backend.controller.Controller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RestController
@Slf4j
@RequiredArgsConstructor
public class RestApi {

    private Controller controller = new Controller();

    @PostMapping("api/mapInit")
    public response mapInit(@RequestBody @Validated createRequest request) {
        response result;
        Vector temp = new Vector();

        List<Vector> size = temp.extractVector(request.getSize());
        List<Vector> startPoint = temp.extractVector(request.getStart());
        List<Vector> spotPoint = temp.extractVector(request.getSpot());
        List<Vector> hazardPoint = temp.extractVector(request.getHazard());

        controller.mapInit(size.get(0), startPoint.get(0), spotPoint, hazardPoint);
        controller.getAddOn().getSensorController().setPos(startPoint.get(0));

        List<VectorDto> s = size.stream().map(m -> new VectorDto(m.getX(), m.getY()))
                .collect(Collectors.toList());
        List<VectorDto> sp = startPoint.stream().map(m -> new VectorDto(m.getX(), m.getY()))
                .collect(Collectors.toList());
        List<VectorDto> spp = spotPoint.stream().map(m -> new VectorDto(m.getX(), m.getY()))
                .collect(Collectors.toList());
        List<VectorDto> hp = hazardPoint.stream().map(m -> new VectorDto(m.getX(), m.getY()))
                .collect(Collectors.toList());

        return new response(s, sp, spp, hp);
    }

    @GetMapping("/api/pathfinding")
    public Result path() {
        List<Vector> path = controller.pathFinding(controller.getMap());
        List<PathDto> result = path.stream().map(m -> new PathDto(m.getX(), m.getY())).collect(Collectors.toList());

        return new Result(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T path;
    }

    @Data
    @AllArgsConstructor
    static class PathDto {
        private int x;
        private int y;
    }

    @Data
    static class createRequest {
        private String size;
        private String start;
        private String spot;
        private String hazard;
    }


    @Data
    static class response {
        private List<VectorDto> size;
        private List<VectorDto> start;
        private List<VectorDto> spots;
        private List<VectorDto> hazards;

        public response(List<VectorDto> size, List<VectorDto> start, List<VectorDto> spots, List<VectorDto> hazards) {
            this.size = size;
            this.start = start;
            this.spots = spots;
            this.hazards = hazards;
        }
    }

    @Data
    @AllArgsConstructor
    static class VectorDto {

        private int x;
        private int y;

    }

}
