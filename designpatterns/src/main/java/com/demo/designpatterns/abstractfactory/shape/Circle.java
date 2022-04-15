package com.demo.designpatterns.abstractfactory.shape;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:12
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
