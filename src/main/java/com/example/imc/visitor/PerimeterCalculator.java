package com.example.imc.visitor;

public class PerimeterCalculator implements Shape.Visitor<Double> {
    public double calculatePerimeter(Shape shape) {
        return shape.accept(this);
    }

    @Override
    public Double visitRectangleShape(Shape.Rectangle shape) {
        return (shape.width + shape.length) * 2;
    }

    @Override
    public Double visitCircleShape(Shape.Circle shape) {
        return 2 * Math.PI * shape.radius;
    }

    @Override
    public Double visitTriangleShape(Shape.Triangle shape) {
        return shape.sideA + shape.sideB + shape.sideC;
    }
}
