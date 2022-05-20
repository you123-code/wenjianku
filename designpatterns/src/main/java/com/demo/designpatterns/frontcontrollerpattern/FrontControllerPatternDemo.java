package com.demo.designpatterns.frontcontrollerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/20 0:02
 * 前端控制器模式
 */
public class FrontControllerPatternDemo {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();
        frontController.dispatchRequest("home");
        frontController.dispatchRequest("student");
    }
}
