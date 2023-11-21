package com.SWEProject.BackEnd.model;

import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.dto.ResponseVectorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Converter {

    private final static String vectorFormatRegex = "(\\d{1,10} \\d{1,10})";
    private final static String vectorFormat = "(%d, %d)";

    public static String convertVectorToString(Vector vector) {
        return String.format(vectorFormat, vector.getX(), vector.getY());
    }

    public static List<Vector> convertStringToVector(String input) {
        Pattern p = Pattern.compile(vectorFormatRegex);
        Matcher matcher = p.matcher(input);
        List<Vector> result = new ArrayList<>();

        while (matcher.find()) {
            String[] split = matcher.group().split(" ");
            result.add(Vector.of(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        return result;
    }

    public static String convertVectorToJson(List<Vector> vectors) throws JsonProcessingException {

        List<ResponseVectorDto> vectorDto = vectors.stream()
                .map(vector -> new ResponseVectorDto(convertVectorToString(vector))).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(vectorDto);

        return result;
    }
}


