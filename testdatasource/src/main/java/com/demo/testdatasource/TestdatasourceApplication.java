package com.demo.testdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.demo.testdatasource.mapper")
public class TestdatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestdatasourceApplication.class, args);
    }

}
