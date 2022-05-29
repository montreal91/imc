package com.example.imc.visitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AreaCalculatorTest {
    @ParameterizedTest
    @MethodSource("calculateAreaSource")
    void calculateArea(Shape shape, double expectedArea) {
        AreaCalculator ac = new AreaCalculator();
        Assertions.assertEquals(expectedArea, ac.calculateArea(shape), 0.00001);
    }

    private static Stream<Arguments> calculateAreaSource() {
        return Stream.of(
                Arguments.of(new Shape.Rectangle(2, 3), 6),
                Arguments.of(new Shape.Rectangle(3, 2), 6),
                Arguments.of(new Shape.Rectangle(4.1, 3), 12.3),
                Arguments.of(new Shape.Circle(1), 3.141592),
                Arguments.of(new Shape.Circle(2.34), 17.20210),
                Arguments.of(new Shape.Triangle(3, 4 ,5), 6),
                Arguments.of(new Shape.Triangle(3, 5 ,7), 6.49519)
        );
    }
}
