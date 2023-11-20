package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class createMapRequest {
    private String map;
    private String start;
    private String hazard;
    private String spot;
    private String colorBlobs;
}
