package com.SWEProject.BackEnd.addOn;

import lombok.Data;

@Data
public class VocalRequest {
    private String type;
    private String position;

    public VocalRequest(String type, String position) {
        this.type = type;
        this.position = position;
    }
}
