package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class ResponseDataDto {
    private String path;
    private String hazardList;
    private String colorBlobList;
    private String currentPosition;
    private String direction;


    public ResponseDataDto(String path, String hazardList, String colorBlobList, String currentPosition
            , String direction) {
        this.path = path;
        this.hazardList = hazardList;
        this.colorBlobList = colorBlobList;
        this.currentPosition = currentPosition;
        this.direction = direction;
    }
}

