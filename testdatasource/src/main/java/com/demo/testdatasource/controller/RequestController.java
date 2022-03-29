package com.demo.testdatasource.controller;


import com.demo.testdatasource.model.Request;
import com.demo.testdatasource.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 项目申报信息 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2022-03-11
 */
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @GetMapping("/getUser")
    public Request getUser(String id){
        return requestService.getById(id);
    }

}
