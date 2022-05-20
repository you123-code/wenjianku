package com.demo.designpatterns.frontcontrollerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 23:59
 */
public class FrontController {
    private Dispatcher dispatcher;

    public FrontController(){
        dispatcher = new Dispatcher();
    }

    private boolean isAuthenticUser(){
        System.out.println("User is authenticated successfully.");
        return true;
    }

    private void trackRequest(String request){
        System.out.println("Page requested: " + request);
    }

    public void dispatchRequest(String request){
        trackRequest(request);
        if(isAuthenticUser()){
            dispatcher.disPatch(request);
        }
    }
}
