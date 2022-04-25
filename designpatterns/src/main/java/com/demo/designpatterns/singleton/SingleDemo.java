package com.demo.designpatterns.singleton;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/14 15:03
 * 单例模式
 */
public class SingleDemo {
    public static void main(String[] args) {
        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject singleObject = new SingleObject();
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
        SingleObject object1 = SingleObject.getInstance();
        object1.showMessage();
    }
}
