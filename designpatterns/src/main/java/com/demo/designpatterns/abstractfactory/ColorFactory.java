package com.demo.designpatterns.abstractfactory;

import com.demo.designpatterns.abstractfactory.color.Blue;
import com.demo.designpatterns.abstractfactory.color.Green;
import com.demo.designpatterns.abstractfactory.color.Red;
import com.demo.designpatterns.abstractfactory.shape.Shape;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:18
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public com.demo.designpatterns.abstractfactory.color.Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        }else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
