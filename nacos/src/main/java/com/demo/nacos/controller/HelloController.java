package com.demo.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/2/28 15:37
 */
@RestController
public class HelloController {

    @NacosValue(value = "${helloworld:HelloWorld}", autoRefreshed = true)
    private String hello;

    /**
     * 浏览器访问http://192.168.211.1:8080/hello
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return hello;
    }
}
