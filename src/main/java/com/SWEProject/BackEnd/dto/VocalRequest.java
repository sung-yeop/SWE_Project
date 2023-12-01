package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class VocalRequest {
    String type;
    String position;

    public VocalRequest(String type, String position) {
        this.type = type;
        this.position = position;
    }
}
