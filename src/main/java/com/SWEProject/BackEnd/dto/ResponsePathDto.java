package com.SWEProject.BackEnd.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePathDto {

    private List<ResponseVectorDto> path;
    private List<ResponseVectorDto> hazardList;
    private List<ResponseVectorDto> colorBlobList;
    private List<ResponseVectorDto> spotList;
    private ResponseVectorDto currentPosition;


    public ResponsePathDto(List<ResponseVectorDto> path, List<ResponseVectorDto> hazardList
            , List<ResponseVectorDto> colorBlobList, List<ResponseVectorDto> spotList, ResponseVectorDto currentPosition) {
        this.path = path;
        this.hazardList = hazardList;
        this.colorBlobList = colorBlobList;
        this.currentPosition = currentPosition;
    }
}

