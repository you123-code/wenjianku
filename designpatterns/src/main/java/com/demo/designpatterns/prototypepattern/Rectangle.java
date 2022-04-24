package com.demo.designpatterns.prototypepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 14:58
 */
public class Rectangle extends Shape{

    public Rectangle(){
        type = "Rectangle";
    }
    @Override
    void draw() {
        System.out.println("Rectangle");
    }
}
