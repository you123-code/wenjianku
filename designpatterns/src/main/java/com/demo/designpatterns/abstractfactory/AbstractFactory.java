package com.demo.designpatterns.abstractfactory;

import com.demo.designpatterns.abstractfactory.color.Color;
import com.demo.designpatterns.abstractfactory.shape.Shape;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:07
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
