package com.demo.designpatterns.bridge;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 17:57
 */
public class Circle extends Shape{
    private int x,y,radius;

    public Circle(DrawAPI drawAPI, int x, int y, int radius) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
