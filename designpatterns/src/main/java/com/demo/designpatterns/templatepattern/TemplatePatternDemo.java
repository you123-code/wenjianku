package com.demo.designpatterns.templatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:14
 * 模板模式
 */
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
