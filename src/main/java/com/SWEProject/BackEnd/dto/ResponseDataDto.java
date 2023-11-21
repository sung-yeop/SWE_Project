package com.SWEProject.BackEnd.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDataDto {

    private List<ResponseVectorDto> path;
    private List<ResponseVectorDto> hazardList;
    private List<ResponseVectorDto> colorBlobList;
    private ResponseVectorDto currentPosition;


    public ResponseDataDto(List<ResponseVectorDto> path, List<ResponseVectorDto> hazardList
            , List<ResponseVectorDto> colorBlobList, ResponseVectorDto currentPosition) {
        this.path = path;
        this.hazardList = hazardList;
        this.colorBlobList = colorBlobList;
        this.currentPosition = currentPosition;
    }
}

