package com.demo.suanfa.aspectj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/2 17:38
 */
@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        a.getBean(DataService.class).saveData();
    }
}
