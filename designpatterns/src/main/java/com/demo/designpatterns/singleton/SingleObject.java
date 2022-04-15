package com.demo.designpatterns.singleton;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 14:57
 */
public class SingleObject {
    //创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();
    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){};

    public static SingleObject getInstance(){
        return instance;
    }
    public void showMessage(){
        System.out.println("hello");
    }


}
