package com.demo.designpatterns.mediatorpattern;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/29 13:46
 */
public class User {
    private String name;
    public String getName(){
        return name;
    }
    public void  setName(String name){
        this.name = name;
    }
    public User(String name){
        this.name = name;
    }
    public void sendMessage(String message){
        ChatRoom.showMessage(this,message);
    }
}
