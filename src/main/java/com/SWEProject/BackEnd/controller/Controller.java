package com.SWEProject.BackEnd.controller;

import com.SWEProject.BackEnd.addOn.AddOn;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.ResponsePathDto;
import com.SWEProject.BackEnd.dto.ResponseVectorDto;
import com.SWEProject.BackEnd.dto.createMapRequest;
import com.SWEProject.BackEnd.dto.moveRequest;
import lombok.RequiredArgsConstructor;
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
public class Controller {

    private AddOn addOn;
    private Map map;
    private List<Vector> path;

    @PostMapping("api/init")
    public void init(@RequestBody @Validated createMapRequest request) {

        createMap(request);

        addOn = new AddOn(map.getStartPoint());
        path = addOn.pathFind(map);

//        ResponseVectorDto responseCurrentPosDto = new ResponseVectorDto(
//                convertVectorToString(map.getStartPoint()));
//        List<ResponseVectorDto> responsePathDto = path.stream()
//                .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());
//        List<ResponseVectorDto> responseHazardsDto = map.getHazardList().stream()
//                .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());
//        List<ResponseVectorDto> responseSpotsDto = map.getSpotList().stream()
//                .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());
//        List<ResponseVectorDto> responseColorBlobsDto = map.getColorblobList().stream()
//                .map(v -> new ResponseVectorDto(convertVectorToString(v))).collect(Collectors.toList());
//
//        return new ResponsePathDto(responsePathDto, responseHazardsDto, responseColorBlobsDto,
//                responseSpotsDto, responseCurrentPosDto);
    }

    private void createMap(createMapRequest request) {
        map = new Map(convertStringToVector(request.getSize()).stream().findFirst().get(),
                convertStringToVector(request.getStart()).stream().findFirst().get(),
                convertStringToVector(request.getSpots()),
                convertStringToVector(request.getHazards()),
                convertStringToVector(request.getColorBlobs()));
    }

    @GetMapping("api/move")
    public ResponsePathDto move(@RequestBody @Validated moveRequest request) {
        Vector nextPosition = convertStringToVector(request.getNextPosition()).get(0);

        //nextPosition에 맞춰서 로봇을 회전시키는 로직 추가 필요

        return problemWithCourse(nextPosition);
    }

    private ResponsePathDto problemWithCourse(Vector nextPosition) {
        boolean moveFlag = true;
        List<ResponseVectorDto> responsePathDtos = null;
        List<ResponseVectorDto> responseHazardList = null;
        List<ResponseVectorDto> responseSpotList = null;
        List<ResponseVectorDto> responseColorblobList = null;
        ResponseVectorDto responseCurrentPosition = null;

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

        if (moveFlag) {
            addOn.move(nextPosition);
            // TODO : nextPosition이 필요하지 않도록 로직 수정 필요
        }

        responseCurrentPosition = new ResponseVectorDto(convertVectorToString(addOn.getCurrentPosition()));

        return new ResponsePathDto(responsePathDtos,
                responseHazardList, responseColorblobList, responseSpotList, responseCurrentPosition);
    }
}

