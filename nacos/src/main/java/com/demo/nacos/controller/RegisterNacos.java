package com.demo.nacos.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author youwei
 * @version 1.0
 * @description://【 Nacos Api注册服务方式 】: curl -X PUT 'http://www.zhengqingya.com:8848/nacos/v1/ns/instance?serviceName=example&ip=127.0.0.1&port=8080'
 * @date 2022/2/28 15:38
 */
@Component
public class RegisterNacos {


    @NacosInjected
    private NamingService namingService;

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 注册服务
     *
     * @throws NacosException
     */
    @PostConstruct // 修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次！！！
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "192.168.211.132", serverPort);
    }
}
