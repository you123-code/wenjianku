package com.demo.designpatterns.bridge;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 17:54
 */
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI  = drawAPI;
    }
    public abstract void draw();
}
