package com.demo.designpatterns.factory;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 11:39
 */
public class FactoryDemo {
    public static void main(String[] args) {
        ShapeFactory shape = new ShapeFactory();
        Shape shape1  = shape.getShape("circle");
        shape1.draw();
        Shape shape2 = shape.getShape("rectangle");
        shape2.draw();
        Shape shape3 = shape.getShape("square");
        shape3.draw();
    }
}
