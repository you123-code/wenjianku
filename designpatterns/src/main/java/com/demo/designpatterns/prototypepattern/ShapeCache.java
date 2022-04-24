package com.demo.designpatterns.prototypepattern;

import java.util.Hashtable;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 15:05
 */
public class ShapeCache {

    public static Hashtable<String,Shape> shapeMap = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId){
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }
    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状

    public static void loanCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        square.setId("3");
        shapeMap.put(square.getId(),rectangle);
    }
}
