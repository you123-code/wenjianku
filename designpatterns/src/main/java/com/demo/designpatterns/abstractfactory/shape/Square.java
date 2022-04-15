package com.demo.designpatterns.abstractfactory.shape;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:11
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
