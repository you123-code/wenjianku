package com.demo.neibulei;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/28 16:52
 */
public class NeiBuLei {
    private  int a = 0;
    private static int b = 1;
    private T t;
    //非静态成员内部类
    public  class T{
        private static final int c = 3;
        public    void u(){
            System.out.println("内部类u方法");
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
    }
    //局部内部类
    public static  void u(){
        int l = 0;
        class U{

        }
        System.out.println("成员u方法");
    }
    //静态成员内部类
    public static class X{
        public static void f(){
            System.out.println(b);
        }
    }
    public T getT(){
        return new T();
    }
    public static void main(String[] args) {
        NeiBuLei neiBuLei = new NeiBuLei();
        //两种获取内部类的方法
        neiBuLei.getT().u();
        neiBuLei.new T().u();
        //获取静态成员内部类的静态方法
        NeiBuLei.X.f();
    }
}
