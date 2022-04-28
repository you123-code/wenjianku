package com.demo.designpatterns.flyweightpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/27 18:01
 * 享元模式
 */
public class FlyweightPatternDemo {
    private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setx(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static String getRandomColor(){
        System.out.println("Math.random()*colors.length = " + Math.random() * colors.length);
        return colors[(int) (Math.random()*colors.length)];
    }
    private static int getRandomX(){
        return (int) (Math.random()*100);
    }
    private static int getRandomY(){
        return (int) (Math.random()*100);
    }
}
