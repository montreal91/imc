package com.example.imc.visitor;

public class AreaCalculator implements Shape.Visitor<Double> {
    public double calculateArea(Shape shape) {
        return shape.accept(this);
    }

    @Override
    public Double visitRectangleShape(Shape.Rectangle shape) {
        return shape.length * shape.width;
    }

    @Override
    public Double visitCircleShape(Shape.Circle shape) {
        return Math.PI * shape.radius * shape.radius;
    }

    @Override
    public Double visitTriangleShape(Shape.Triangle shape) {
        double p = (shape.sideA + shape.sideB + shape.sideC) / 2;
        return Math.sqrt(p * (p - shape.sideA) * (p-shape.sideB) * (p - shape.sideC));
    }
}
