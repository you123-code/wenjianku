package com.demo.testbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/1/31 11:09
 */
@Configuration
@ComponentScan("com.demo.testbean")
public class TestBean {
    @Bean("springIocDate")
    public Date createDate() {
        System.out.println("测试");
        return new Date();
    }
}
