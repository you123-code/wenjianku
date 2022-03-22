package com.demo.testdatasource.service.impl;

import com.demo.testdatasource.model.Request;
import com.demo.testdatasource.mapper.RequestMapper;
import com.demo.testdatasource.service.RequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目申报信息 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-03-11
 */
@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements RequestService {

}
