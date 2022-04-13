package com.demo.testdatasource.controller;


import com.demo.testdatasource.model.Request;
import com.demo.testdatasource.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Request> getUser(String id){
        List<Request> requests  = new ArrayList<>();
        List<String> strings = Arrays.asList(id.split(","));
        strings.forEach(item->{
           Request request = requestService.getById(item);
           requests.add(request);
        });
        return requests;
    }
}
