package com.demo.designpatterns.prototypepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 15:00
 */
public class Square extends Shape {
    public Square(){
        type = "Square";
    }
    @Override
    void draw() {
        System.out.println("Square");
    }
}
