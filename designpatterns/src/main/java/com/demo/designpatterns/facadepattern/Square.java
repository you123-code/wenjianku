package com.demo.designpatterns.facadepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 14:52
 */
public class Square  implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
