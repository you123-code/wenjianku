package com.demo.designpatterns.facadepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 14:58
 * 外观模式
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
