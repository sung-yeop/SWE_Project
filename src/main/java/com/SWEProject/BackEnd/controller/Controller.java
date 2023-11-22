package com.SWEProject.BackEnd.controller;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.SWEProject.BackEnd.model.Converter.convertStringToVector;
import static com.SWEProject.BackEnd.model.Converter.convertVectorToString;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private AddOn addOn;
    private Map map;
    private List<Vector> path;

    @PostMapping("/api/init/")
    public ResponseStringDto init(@RequestBody @Validated createMapRequest request) {

        createMap(request);

        addOn = new AddOn(map.getStartPoint());
        path = addOn.pathFind(map);
        path.remove(path.stream().findFirst().get());

        log.info("init");

        String output = "[" + this.path.stream()
                .map(vector -> convertVectorToString(vector)).collect(Collectors.joining(", ")) + "]";

        return new ResponseStringDto(output);
    }

    private void createMap(createMapRequest request) {
        map = new Map(convertStringToVector(request.getMap()).stream().findFirst().get(),
                convertStringToVector(request.getStart()).stream().findFirst().get(),
                convertStringToVector(request.getHazard()),
                convertStringToVector(request.getSpot()),
                convertStringToVector(request.getColorBlob()));
    }

    @PostMapping("/api/move/")
    public ResponseDataDto move(@RequestBody @Validated moveRequest request) {
        Vector nextPosition = convertStringToVector(request.getPath()).get(0);

        log.info(String.valueOf(request));

        if (nextPosition.equals(addOn.getCurrentPosition())) {

            ResponseDataDto responseDataDto = new ResponseDataDto(null, null, null,
                    (convertVectorToString(addOn.getCurrentPosition())));
            return responseDataDto;
        }

        //nextPosition에 맞춰서 로봇을 회전시키는 로직 추가 필요

        return problemWithCourse(nextPosition);
    }

    private ResponseDataDto problemWithCourse(Vector nextPosition) {
        boolean moveFlag = true;
        String responsePathDtos = null;
        String responseHazardList = null;
        String responseColorblobList = null;
        String responseCurrentPosition = null;

        addOn.directionSetting(nextPosition); //목표 지점으로 방향 전환

        //이후 센서 작동
        if (addOn.moveWithHazardSense(map)) {
            responseHazardList = "[" + map.getHazardList().stream()
                    .map(v -> convertVectorToString(v)).collect(Collectors.joining(", ")) + "]";

            if (map.getHazardList().stream().anyMatch(v -> v.equals(nextPosition))) {
                path = addOn.pathFind(map);
                path.remove(path.stream().findFirst().get());
                responsePathDtos = "[" + path.stream().map(v -> convertVectorToString(v))
                        .collect(Collectors.joining(", ")) + "]";
            }
            moveFlag = false;
        }

        if (addOn.moveWithColorBlobSense(map)) {
            responseColorblobList = "[" + map.getColorblobList().stream().map(v -> convertVectorToString(v))
                    .collect(Collectors.joining(", ")) + "]";
        }

//        if (nextPosition.equals(addOn.getCurrentPosition())) {
//            moveFlag = false;
//        }

        if (addOn.getCurrentPosition().equals(nextPosition)) {
            moveFlag = false;
        }

        //문제 없으면 이동
        if (moveFlag) {
            addOn.move();
//            addOn.addCheckSpot(map.getSpotList());
        }

        if (map.getSpotList().stream().anyMatch(v -> v.equals(addOn.getCurrentPosition()))) { //탐지했으면 찾아야할 Spotlist에서 삭제
            addOn.setCheckSpot(addOn.getCurrentPosition());
        }

//        //움직임 이후 문제가 존재하는지 확인
//        if (addOn.moveWithError(nextPosition) && moveFlag) {
//            path = addOn.pathFind(map);
//            path.remove(path.stream().findFirst().get());
//            responsePathDtos = "[" + path.stream().map(v -> convertVectorToString(v))
//                    .collect(Collectors.joining(", ")) + "]";
//        }

        responseCurrentPosition = (convertVectorToString(addOn.getCurrentPosition()));

        String cu = String.format("현재 위치 : (%d, %d)", addOn.getCurrentPosition().getX(), addOn.getCurrentPosition().getY());
        log.info(cu);
        log.info(responsePathDtos);

        return new ResponseDataDto(responsePathDtos,
                responseHazardList, responseColorblobList, responseCurrentPosition);
    }
}

