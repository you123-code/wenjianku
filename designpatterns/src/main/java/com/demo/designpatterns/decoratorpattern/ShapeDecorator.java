package com.demo.designpatterns.decoratorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 15:50
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;
    public ShapeDecorator(Shape shape){
        this.decoratedShape = shape;
    }
    public void draw(){
        decoratedShape.draw();
    }
}
