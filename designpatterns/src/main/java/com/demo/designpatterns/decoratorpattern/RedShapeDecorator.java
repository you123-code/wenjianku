package com.demo.designpatterns.decoratorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/25 15:52
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(){
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    public void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
