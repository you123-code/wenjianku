package com.demo.designpatterns.observerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/8 17:57
 * 观察者模式
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        subject.notifyAllObservers();
        System.out.println("Second state change: 10");
        subject.setState(10);
        subject.notifyAllObservers();
    }
}
