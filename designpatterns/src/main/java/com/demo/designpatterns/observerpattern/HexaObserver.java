package com.demo.designpatterns.observerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/8 17:44
 */
public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
