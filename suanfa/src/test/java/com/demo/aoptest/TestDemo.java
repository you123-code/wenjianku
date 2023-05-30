package com.demo.aoptest;

import java.util.Scanner;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/9 20:50
 */

public class TestDemo {
    public  static String names[] = new String[5];
    public static void main(String[] args) {
       Test test = new Test();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String next = scanner.next();
            test.addName(next);
        }
    }
    public static class Test{
        public void addName(String name){
            for (int i = 0; i < names.length; i++) {
                if(names[i] == null){
                    names[i] = name;
                    break;
                }
            }
        }
    }

}
