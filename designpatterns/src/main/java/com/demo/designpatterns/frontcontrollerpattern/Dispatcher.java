package com.demo.designpatterns.frontcontrollerpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/5/19 23:47
 */
public class Dispatcher {
    private HomeView homeView;
    private StudentView studentView;

    public Dispatcher(){
        homeView = new HomeView();
        studentView = new StudentView();
    }

    public void disPatch(String request){
        if(request.equalsIgnoreCase("student")){
            studentView.show();
        }else{
            homeView.show();
        }
    }
}
