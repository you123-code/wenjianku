package com.demo.designpatterns.templatepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/10 11:50
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}
