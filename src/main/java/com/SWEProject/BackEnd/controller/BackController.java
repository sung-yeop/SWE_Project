package com.SWEProject.BackEnd.controller;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.*;
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
public class BackController {
    private AddOn addOn;
    private Map map;
    private List<Vector> path;
    private final String COMMA = ", ";
    private final String COMPLETE = "complete";
    private final int FIRST = 0;
    private final int NONE = 0;
    private final int SIZEVAL = 1;

    @PostMapping("/api/vocal/")
    public void initHidden(@RequestBody @Validated VocalRequest request) {
        Vector position = convertStringToVector(request.getPosition()).stream().findFirst().get();
        if (request.getType().contains("중요")) {
            addOn.addHiddenColor(position);
        }
        if (request.getType().contains("위험")) {
            addOn.addHiddenHazard(position);
        }

        log.info(request.toString());
    }

    @PostMapping("/api/init/")
    public ResponseStringDto init(@RequestBody @Validated createMapRequest request) {
        createMap(request);

        addOn = new AddOn(map.getStartPoint(), map.getHazardList(), map.getColorblobList());
        path = addOn.pathFind(map.getSize(), map.getMapInit(), map.getSpotList());
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
        String responseDirection = null;
        addOn.directionSetting(nextPosition); //목표 지점으로 방향 전환
        Vector beforePosition = Vector.deepClone(addOn.getCurrentPosition());
        List<Vector> hazards = addOn.getCheckHazards();
        List<Vector> colors = addOn.getCheckColorblobs();
        List<Vector> spots = map.getSpotList();
        Vector size = map.getSize();

        //이후 센서 작동
        if (addOn.moveWithHazardSense()) {
            responseHazardList = "[" + hazards.stream()
                    .map(Converter::convertVectorToString).collect(Collectors.joining(COMMA)) + "]";

            if (hazards.stream().anyMatch(v -> v.equals(nextPosition))) {
                path = addOn.pathFind(size, map.getMapInit(), spots);
                responsePathDtos = "[" + path.stream().map(Converter::convertVectorToString)
                        .collect(Collectors.joining(COMMA)) + "]";
            }
            moveFlag = false;
        }

        if (addOn.moveWithColorBlobSense()) {
            responseColorblobList = "[" + colors.stream().map(Converter::convertVectorToString)
                    .collect(Collectors.joining(COMMA)) + "]";
        }

        if (addOn.getCurrentPosition().equals(nextPosition)) {
            moveFlag = false;
        }

        //문제 없으면 이동
        if (moveFlag) {
            addOn.move();
            while (validateMovement(size, hazards, addOn.getCurrentPosition())) { //맵 밖으로 나가는 경우 및 Hazard로 이동하는 경우를 제어
                addOn.setPosition(beforePosition);
                addOn.move();
            }
        }

        if (spots.stream().anyMatch(v -> v.equals(addOn.getCurrentPosition()))) {
            Vector vector = spots.stream()
                    .filter(v -> v.equals(addOn.getCurrentPosition())).findFirst().get();
            spots.remove(vector);
        }

        //움직임 이후 문제가 존재하는지 확인
        if (addOn.moveWithError(nextPosition) && moveFlag) {
            path = addOn.pathFind(size, map.getMapInit(), spots);
            responsePathDtos = "[" + path.stream().map(v -> convertVectorToString(v))
                    .collect(Collectors.joining(COMMA)) + "]";
        }

        responseCurrentPosition = (convertVectorToString(addOn.getCurrentPosition()));
        responseDirection = addOn.getDirection().toString();

        return new ResponseDataDto(responsePathDtos,
                responseHazardList, responseColorblobList, responseCurrentPosition, responseDirection);
    }
}

