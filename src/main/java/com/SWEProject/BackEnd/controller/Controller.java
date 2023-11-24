package com.SWEProject.BackEnd.controller;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.ResponseDataDto;
import com.SWEProject.BackEnd.dto.ResponseStringDto;
import com.SWEProject.BackEnd.dto.createMapRequest;
import com.SWEProject.BackEnd.dto.moveRequest;
import com.SWEProject.BackEnd.model.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.SWEProject.BackEnd.model.Converter.convertStringToVector;
import static com.SWEProject.BackEnd.model.Converter.convertVectorToString;
import static com.SWEProject.BackEnd.validate.ValidateMovement.validateMovement;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private AddOn addOn;
    private Map map;
    private List<Vector> path;
    private final String COMMA = ", ";
    private final String COMPLETE = "complete";
    private final int FIRST = 0;
    private final int NONE = 0;
    private final int SIZEVAL = 1;

    @PostMapping("/api/init/")
    public ResponseStringDto init(@RequestBody @Validated createMapRequest request) {
        createMap(request);

        addOn = new AddOn(map.getStartPoint());
        path = addOn.pathFind(map);
        path.remove(path.stream().findFirst().get());

        String output = "[" + this.path.stream()
                .map(Converter::convertVectorToString).collect(Collectors.joining(COMMA)) + "]";

        return new ResponseStringDto(output);
    }

    private void createMap(createMapRequest request) {
        Vector inputSize = convertStringToVector(request.getMap()).stream().findFirst().get();
        Vector size = Vector.of(inputSize.getX() + SIZEVAL, inputSize.getY() + SIZEVAL);

        map = new Map(size,
                convertStringToVector(request.getStart()).stream().findFirst().get(),
                convertStringToVector(request.getHazard()),
                convertStringToVector(request.getSpot()),
                convertStringToVector(request.getColorBlob()));
    }

    @PostMapping("/api/move/")
    public ResponseDataDto move(@RequestBody @Validated moveRequest request) {
        Vector nextPosition = convertStringToVector(request.getPath()).get(FIRST);

        if (nextPosition.equals(addOn.getCurrentPosition())) {
            ResponseDataDto responseDataDto = new ResponseDataDto(null, null, null,
                    (convertVectorToString(addOn.getCurrentPosition())), null);
            return responseDataDto;
        }

        return problemWithCourse(nextPosition);
    }

    private ResponseDataDto problemWithCourse(Vector nextPosition) {
        boolean moveFlag = true;
        String responsePathDtos = null;
        String responseHazardList = null;
        String responseColorblobList = null;
        String responseCurrentPosition = null;
        String responseComplete = null;
        addOn.directionSetting(nextPosition); //목표 지점으로 방향 전환
        Vector beforePosition = Vector.deepClone(addOn.getCurrentPosition());

        //이후 센서 작동
        if (addOn.moveWithHazardSense(map)) {
            responseHazardList = "[" + map.getHazardList().stream()
                    .map(Converter::convertVectorToString).collect(Collectors.joining(COMMA)) + "]";

            if (map.getHazardList().stream().anyMatch(v -> v.equals(nextPosition))) {
                path = addOn.pathFind(map);
                responsePathDtos = "[" + path.stream().map(Converter::convertVectorToString)
                        .collect(Collectors.joining(COMMA)) + "]";
            }
            moveFlag = false;
        }

        if (addOn.moveWithColorBlobSense(map)) {
            responseColorblobList = "[" + map.getColorblobList().stream().map(Converter::convertVectorToString)
                    .collect(Collectors.joining(COMMA)) + "]";
        }

        if (addOn.getCurrentPosition().equals(nextPosition)) {
            moveFlag = false;
        }

        //문제 없으면 이동
        if (moveFlag) {
            addOn.move();
            while (validateMovement(map, addOn.getCurrentPosition())) { //맵 밖으로 나가는 경우 및 Hazard로 이동하는 경우를 제어
                addOn.setPosition(beforePosition);
                addOn.move();
            }
        }

        if (map.getSpotList().stream().anyMatch(v -> v.equals(addOn.getCurrentPosition()))) {
            Vector vector = map.getSpotList().stream()
                    .filter(v -> v.equals(addOn.getCurrentPosition())).findFirst().get();
            map.getSpotList().remove(vector);
        }

        //프론트에서는 "Complete"가 넘어오면 완료되었음을 표시
        if (map.getSpotList().size() == NONE) {
            responseComplete = COMPLETE;
        }

        //움직임 이후 문제가 존재하는지 확인
        if (addOn.moveWithError(nextPosition) && moveFlag) {
            path = addOn.pathFind(map);
            responsePathDtos = "[" + path.stream().map(v -> convertVectorToString(v))
                    .collect(Collectors.joining(COMMA)) + "]";
        }

        responseCurrentPosition = (convertVectorToString(addOn.getCurrentPosition()));

        return new ResponseDataDto(responsePathDtos,
                responseHazardList, responseColorblobList, responseCurrentPosition, responseComplete);
    }
}

