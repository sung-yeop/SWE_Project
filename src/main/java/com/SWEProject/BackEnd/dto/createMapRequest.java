package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class createMapRequest {
    private String size;
    private String start;
    private String hazards;
    private String spots;
    private String colorBlobs;
}
