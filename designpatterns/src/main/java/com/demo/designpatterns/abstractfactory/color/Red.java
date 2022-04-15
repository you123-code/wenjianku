package com.demo.designpatterns.abstractfactory.color;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:04
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
