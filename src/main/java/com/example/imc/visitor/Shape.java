package com.example.imc.visitor;

public abstract class Shape {
    interface Visitor<R> {
        R visitRectangleShape(Rectangle shape);
        R visitCircleShape(Circle shape);
        R visitTriangleShape(Triangle shape);
    }

    static class Rectangle extends Shape {
        final double length;
        final double width;

        Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitRectangleShape(this);
        }
    }

    static class Circle extends Shape {
        final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCircleShape(this);
        }
    }

    static class Triangle extends Shape {
        final double sideA;
        final double sideB;
        final double sideC;

        Triangle(double sideA, double sideB, double sideC) {
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitTriangleShape(this);
        }
    }

    abstract <R> R accept(Visitor<R> visitor);
}
