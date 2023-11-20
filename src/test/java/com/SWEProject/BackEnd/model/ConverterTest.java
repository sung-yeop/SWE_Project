package com.SWEProject.BackEnd.model;

import com.SWEProject.BackEnd.domain.Vector;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class ConverterTest {

    private Converter converter;

    @BeforeEach
    void setConverter() {
        converter = new Converter();
    }

    @DisplayName("String을 Vector 형식으로 변환하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = "(4 5)")
    void 문자열을_Vector로_변환하고_리스트_반환_테스트(String input) {
        List<Vector> result = converter.convertStringToVector(input);

        Assertions.assertThat(result.get(0).getX()).isEqualTo(4);
        Assertions.assertThat(result.get(0).getY()).isEqualTo(5);
    }

    @DisplayName("Json 형태로 데이터가 출력되는지 확인")
    @Test
    void Vector_리스트에서_Json_형태로_변환하는_기능_테스트() throws JsonProcessingException {
        List<Vector> vectors = new ArrayList<>();
        vectors.add(Vector.of(3, 4));
        vectors.add(Vector.of(4, 5));

        System.out.println(converter.convertVectorToJson(vectors));
    }


}