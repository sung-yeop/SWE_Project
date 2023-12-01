package com.SWEProject.BackEnd.addOn;

import lombok.Data;

@Data
public class createMapRequest {
    private String map;
    private String start;
    private String hazard;
    private String spot;
    private String colorBlob;
}
