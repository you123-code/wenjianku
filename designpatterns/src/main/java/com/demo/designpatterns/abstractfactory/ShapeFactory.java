package com.demo.designpatterns.abstractfactory;

import com.demo.designpatterns.abstractfactory.color.Color;
import com.demo.designpatterns.abstractfactory.shape.Circle;
import com.demo.designpatterns.abstractfactory.shape.Rectangle;
import com.demo.designpatterns.abstractfactory.shape.Shape;
import com.demo.designpatterns.abstractfactory.shape.Square;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:14
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if(shape == null){
            return null;
        }
        if(shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
