package com.demo.designpatterns.mementopattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/5 16:46
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
