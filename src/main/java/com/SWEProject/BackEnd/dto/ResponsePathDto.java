package com.SWEProject.BackEnd.dto;

import com.SWEProject.BackEnd.domain.Vector;
import lombok.Data;

import java.util.List;

@Data
public class ResponsePathDto {

    private List<ResponseVectorDto> path;

    public ResponsePathDto(List<ResponseVectorDto> path) {
        this.path = path;
    }
}
