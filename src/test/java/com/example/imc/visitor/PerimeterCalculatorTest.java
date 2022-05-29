package com.example.imc.visitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PerimeterCalculatorTest {
    @ParameterizedTest
    @MethodSource("calculatePerimeterSource")
    void calculateShapePerimeter(Shape shape, double expectedResult) {
        PerimeterCalculator pc = new PerimeterCalculator();
        Assertions.assertEquals(expectedResult, pc.calculatePerimeter(shape), 0.00001);
    }

    private static Stream<Arguments> calculatePerimeterSource() {
        return Stream.of(
                Arguments.of(new Shape.Rectangle(2, 3), 10),
                Arguments.of(new Shape.Rectangle(3, 2), 10),
                Arguments.of(new Shape.Rectangle(4.1, 3), 14.2),
                Arguments.of(new Shape.Circle(1), 6.283184),
                Arguments.of(new Shape.Circle(2.34), 14.70265),
                Arguments.of(new Shape.Triangle(3, 4 ,5), 12),
                Arguments.of(new Shape.Triangle(3, 5 ,7), 15)
        );
    }
}
