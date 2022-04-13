package com.demo.testdatasource.controller;


import com.demo.testdatasource.model.Request;
import com.demo.testdatasource.service.RequestService;
import com.demo.testdatasource.utils.Consts;
import com.demo.testdatasource.utils.PubTool;
import com.demo.testdatasource.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private PubTool pubTool;
    @GetMapping("/getRequest")
    public List<Request> getRequest(String id){
        List<Request> requests  = new ArrayList<>();
        List<String> strings = Arrays.asList(id.split(","));
        strings.forEach(item->{
           Request request = requestService.getById(item);
           requests.add(request);
        });
        return requests;
    }

    @PostMapping("/addRequest")
    public ResponseResult addRequest(@RequestBody List<Request> requests){
        requests.forEach(item->{
            requestService.save(item);
        });
        return ResponseResult.ok();
    }

    @DeleteMapping("/delRequest")
    public ResponseResult delRequest(String id){
        List<String> strings = Arrays.asList(id.split(","));
        strings.forEach(item->{
            if(pubTool.selectNum(item,requestService) != Consts.Num.ONE){
                log.info("数据不存在id为{}",item);
            }else{
                requestService.removeById(item);
            }
        });
        return ResponseResult.ok();
    }
}
