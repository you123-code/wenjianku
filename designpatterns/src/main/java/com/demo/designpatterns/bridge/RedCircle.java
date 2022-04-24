package com.demo.designpatterns.bridge;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/24 17:51
 */
public class RedCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
