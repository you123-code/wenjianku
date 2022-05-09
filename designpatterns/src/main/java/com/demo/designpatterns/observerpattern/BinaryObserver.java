package com.demo.designpatterns.observerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/6 10:36
 */
public class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
