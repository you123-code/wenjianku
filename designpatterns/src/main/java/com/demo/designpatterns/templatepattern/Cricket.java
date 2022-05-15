package com.demo.designpatterns.templatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 14:45
 */
public class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}
