package com.demo.suanfa.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/2 15:28
 */
@Component
public class DataService implements AspectTest {

    @Override
    public void saveData() {
        System.out.println("save data...");
    }
}
