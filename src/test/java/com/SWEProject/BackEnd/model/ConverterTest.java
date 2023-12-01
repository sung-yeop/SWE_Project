package com.SWEProject.BackEnd.model;

import com.SWEProject.BackEnd.addOn.Converter;
import com.SWEProject.BackEnd.addOn.Vector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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


}