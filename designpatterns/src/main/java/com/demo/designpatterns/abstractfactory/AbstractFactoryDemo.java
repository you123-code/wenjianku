package com.demo.designpatterns.abstractfactory;

import com.demo.designpatterns.abstractfactory.color.Color;
import com.demo.designpatterns.abstractfactory.shape.Shape;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:23
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("shape");
        Shape shape1 = abstractFactory.getShape("circle");
        shape1.draw();
        Shape shape2 = abstractFactory.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3 = abstractFactory.getShape("SQUARE");
        shape3.draw();
        AbstractFactory abstractFactory1 = FactoryProducer.getFactory("color");
        Color color1 = abstractFactory1.getColor("red");
        color1.fill();
        Color color2 = abstractFactory1.getColor("blue");
        color2.fill();
        Color color3 = abstractFactory1.getColor("green");
        color3.fill();
    }
}
