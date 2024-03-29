package com.demo.testdatasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.testdatasource.model.Request;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 项目申报信息 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-03-11
 */
public interface RequestService extends IService<Request> {
    void exportHedgingRegistrationTemplate(HttpServletResponse response);

}
