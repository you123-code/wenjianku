package com.demo.designpatterns.facadepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 14:53
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
