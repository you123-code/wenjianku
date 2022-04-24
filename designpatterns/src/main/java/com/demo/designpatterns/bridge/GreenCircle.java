package com.demo.designpatterns.bridge;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 17:53
 */
public class GreenCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
