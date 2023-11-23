package com.SWEProject.BackEnd.dto;

import com.SWEProject.BackEnd.domain.Vector;
import lombok.Data;

import java.util.List;

@Data
public class ResponseStringDto {

    private String path;

    public ResponseStringDto(String path) {
        this.path = path;
    }
}
