package com.SWEProject.BackEnd.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDataDto {

    private String path;
    private String hazardList;
    private String colorBlobList;
    private String currentPosition;
    private String complete;


    public ResponseDataDto(String path, String hazardList, String colorBlobList, String currentPosition
    , String complete) {
        this.path = path;
        this.hazardList = hazardList;
        this.colorBlobList = colorBlobList;
        this.currentPosition = currentPosition;
        this.complete = complete;
    }
}

