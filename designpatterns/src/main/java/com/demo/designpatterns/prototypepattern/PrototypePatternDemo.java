package com.demo.designpatterns.prototypepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 15:17
 */
public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loanCache();
        Shape shape = ShapeCache.getShape("1");
        System.out.println("shape.getType() = " + shape.getType());
        Shape shape1 = ShapeCache.getShape("2");
        System.out.println("shape1.getType() = " + shape1.getType());
        Shape shape2 = ShapeCache.getShape("3");
        System.out.println("shape2.getType() = " + shape2.getType());
    }
}
