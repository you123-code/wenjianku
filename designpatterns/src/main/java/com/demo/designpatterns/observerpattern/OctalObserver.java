package com.demo.designpatterns.observerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/8 17:33
 */
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
