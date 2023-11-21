package com.SWEProject.BackEnd.controller;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponsePathDto init(@RequestBody @Validated createMapRequest request) {

        createMap(request);

        addOn = new AddOn(map.getStartPoint());
        path = addOn.pathFind(map);

        log.info("init");

        return new ResponsePathDto(
                this.path.stream().map(vector -> new ResponseVectorDto(convertVectorToString(vector)))
                        .collect(Collectors.toList()));
    }

    private void createMap(createMapRequest request) {
        map = new Map(convertStringToVector(request.getMap()).stream().findFirst().get(),
                convertStringToVector(request.getStart()).stream().findFirst().get(),
                convertStringToVector(request.getHazard()),
                convertStringToVector(request.getSpot()),
                convertStringToVector(request.getColorBlob()));
    }

    @GetMapping("/api/move/")
    public ResponseDataDto move(@RequestBody @Validated moveRequest request) {
        Vector nextPosition = convertStringToVector(request.getPath()).get(0);

        //nextPosition에 맞춰서 로봇을 회전시키는 로직 추가 필요

        return problemWithCourse(nextPosition);
    }

    private ResponseDataDto problemWithCourse(Vector nextPosition) {
        boolean moveFlag = true;
        List<ResponseVectorDto> responsePathDtos = null;
        List<ResponseVectorDto> responseHazardList = null;
        List<ResponseVectorDto> responseSpotList = null;
        List<ResponseVectorDto> responseColorblobList = null;
        ResponseVectorDto responseCurrentPosition = null;

        addOn.directionSetting(nextPosition); //목표 지점으로 방향 전환

        //이후 센서 작동
        if (addOn.moveWithHazardSense(map)) {
            responseHazardList = map.getHazardList().stream()
                    .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());


            if (path.stream().anyMatch(m -> map.getHazardList().stream().anyMatch(v -> v.equals(m)))) {
                path = addOn.pathFind(map);
                responsePathDtos = path.stream()
                        .map(v -> new ResponseVectorDto(convertVectorToString(v)))
                        .collect(Collectors.toList());
                responsePathDtos.remove(responsePathDtos.stream().findFirst().get()); // 다음 이동 경로에서 현재 위치 제거
                moveFlag = false;
            }
        }

        if (addOn.moveWithColorBlobSense(map)) {
            responseColorblobList = map.getColorblobList().stream()
                    .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());
        }


        //문제 없으면 이동
        if (moveFlag) {
            addOn.move();
        }

        //움직임 이후 문제가 존재하는지 확인
        if (addOn.moveWithError(nextPosition)) {
            path = addOn.pathFind(map);
            responsePathDtos = path.stream()
                    .map(v -> new ResponseVectorDto(convertVectorToString(v)))
                    .collect(Collectors.toList());
            responsePathDtos.remove(responsePathDtos.stream().findFirst().get());
        }

        responseCurrentPosition = new ResponseVectorDto(convertVectorToString(addOn.getCurrentPosition()));

        return new ResponseDataDto(responsePathDtos,
                responseHazardList, responseColorblobList, responseCurrentPosition);
    }
}

