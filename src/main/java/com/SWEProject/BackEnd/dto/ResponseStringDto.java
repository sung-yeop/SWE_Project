package com.SWEProject.BackEnd.dto;

import lombok.Data;

@Data
public class ResponseStringDto {
    private String path;

    public ResponseStringDto(String path) {
        this.path = path;
    }
}
