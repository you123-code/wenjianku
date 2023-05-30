package com.demo.testdatasource.controller;

import com.demo.testdatasource.mapper.MerchantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/4/22 20:58
 */
@RestController
@RequestMapping("/merchant")
@Slf4j
public class MerchantController {

    @Autowired
    private MerchantMapper merchantMapper;

    @GetMapping(value = "/test")
    public Integer test() {
        System.out.println("连通测试");
        Map<String, Object> map = new HashMap<>();
        System.out.println("测试数量");
        Integer count = merchantMapper.getMerchantCount(map);
        System.out.println("测试数量为："+count);
        return count;
    }
}
