package com.demo.testdatasource.service.impl;

import com.demo.testdatasource.mapper.MerchantMapper;
import com.demo.testdatasource.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/4/22 21:00
 */
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    public Integer getMerchantList(Map<String, Object> map) {
        return  merchantMapper.getMerchantCount(map);
    }
}
