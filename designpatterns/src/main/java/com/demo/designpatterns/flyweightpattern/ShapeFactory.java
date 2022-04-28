package com.demo.designpatterns.flyweightpattern;

import java.util.HashMap;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 17:56
 */
public class ShapeFactory {
    private static final HashMap<String,Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color){
        Circle circle = (Circle) circleMap.get(color);
        if(circle == null){
            circle = new Circle(color);
            circleMap.put(color,circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
