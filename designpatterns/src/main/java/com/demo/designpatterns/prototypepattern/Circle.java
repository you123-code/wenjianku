package com.demo.designpatterns.prototypepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 15:01
 */
public class Circle extends Shape {
    public Circle(){
        type = "Circle";
    }

    @Override
    void draw() {
        System.out.println("Circle");
    }
}
