package com.demo.testitem;

import org.junit.jupiter.api.Test;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/11/8 0:21
 */
public class TestElse {
    @Test
    public void test1(){
        int[] list1 = new int[10];
        list1[0] = 1;
        list1[1] = 2;
        String[] list2 = new String[10];
        System.out.println(list1[0]);
        //System.out.println(list2[0].length());
        for(int i : list1){
            System.out.println(i
            );
        }
    }
}
