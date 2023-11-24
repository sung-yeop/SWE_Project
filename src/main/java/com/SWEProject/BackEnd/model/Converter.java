package com.SWEProject.BackEnd.model;

import com.SWEProject.BackEnd.domain.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    private final static String vectorFormatRegex = "(\\d{1,10} \\d{1,10})";
    private final static String vectorFormat = "(%d, %d)";

    public static String convertVectorToString(Vector vector) {
        return String.format(vectorFormat, vector.getX(), vector.getY());
    }

    public static List<Vector> convertStringToVector(String input) {
        String replace = input.replace(",", "");
        Pattern p = Pattern.compile(vectorFormatRegex);
        Matcher matcher = p.matcher(replace);
        List<Vector> result = new ArrayList<>();

        while (matcher.find()) {
            String[] split = matcher.group().split(" ");
            result.add(Vector.of(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        return result;
    }
}


