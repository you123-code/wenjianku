package com.demo.designpatterns.decoratorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 15:48
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
