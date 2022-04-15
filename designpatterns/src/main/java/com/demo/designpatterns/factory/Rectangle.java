package com.demo.designpatterns.factory;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 11:36
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
