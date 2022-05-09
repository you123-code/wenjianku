package com.demo.designpatterns.statepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 10:33
 */
public class StopState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
