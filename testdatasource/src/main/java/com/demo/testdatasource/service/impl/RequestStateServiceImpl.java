package com.demo.testdatasource.service.impl;

import com.demo.testdatasource.model.RequestState;
import com.demo.testdatasource.mapper.RequestStateMapper;
import com.demo.testdatasource.service.RequestStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务表状态 服务实现类
 * </p>
 *
 * @author youwei
 * @since 2022-04-13
 */
@Service
public class RequestStateServiceImpl extends ServiceImpl<RequestStateMapper, RequestState> implements RequestStateService {

}
