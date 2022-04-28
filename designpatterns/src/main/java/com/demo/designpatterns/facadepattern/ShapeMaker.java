package com.demo.designpatterns.facadepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 14:54
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;
    public ShapeMaker(){
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }
    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
