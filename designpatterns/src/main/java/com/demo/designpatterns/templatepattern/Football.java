package com.demo.designpatterns.templatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 16:13
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}
