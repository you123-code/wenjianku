package com.demo.testdatasource.controller;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/8/22 18:24
 */
public class test {
    public static <E> void printArray(E[] inputArray){
        for (E e : inputArray) {
            System.out.printf("%s\n",e);
        }
        System.out.println("执行结束");
    }

    public  static <T > void printString(T t){

    }

    public static void main(String[] args) {
        Integer[] arr = {1,3,4,5};
        printArray(arr);
        String[] arr1 = {"1","3"};
        printArray(arr1);
    }
}
