package com.demo.designpatterns.bridge;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 18:00
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(new RedCircle(), 100, 100, 10);
        Circle circle1 = new Circle(new GreenCircle(), 100, 100, 10);
        circle.draw();
        circle1.draw();
    }
}
