package com.demo.designpatterns.statepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 10:24
 */
public class Context {
    private State state;

    public Context() {
        this.state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
