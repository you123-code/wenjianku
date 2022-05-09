package com.demo.designpatterns.statepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 10:32
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
