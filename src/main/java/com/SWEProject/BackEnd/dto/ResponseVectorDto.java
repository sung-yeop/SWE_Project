package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class ResponseVectorDto {
    private String vector;

    public ResponseVectorDto(String vector) {
        this.vector = vector;
    }
}
