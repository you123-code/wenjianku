package com.demo.designpatterns.mediatorpattern;

import java.util.Date;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/29 13:46
 */
public class ChatRoom {
    public static void showMessage(User user,String message){
        System.out.println(new Date().toString()
                + " [" + user.getName() +"] : " + message);
    }
}
