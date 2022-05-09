package com.demo.designpatterns.statepattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/9 10:35
 * 状态模式
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
