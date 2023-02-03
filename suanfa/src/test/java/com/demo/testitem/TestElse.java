package com.demo.testitem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/11/8 0:21
 */
@Slf4j
public class TestElse {
    @Test
    public void test1(){
        int i = 1;
        byte b = 2;
        int j = b +i;
        System.out.println(j);
    }
}
