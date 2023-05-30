package com.demo.testdatasource.Aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/3 10:19
 */
@Aspect
@Component
public class PermissionFirstAdvice {
    @Pointcut("@annotation(com.demo.testdatasource.utils.selfinterface.PermissionAnnotation)")
    public void permissionCheck(){}

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("第一个切面"+System.currentTimeMillis());
        //获取请求参数
        Object[] objects = joinPoint.getArgs();
        Long id;String name;
        id = ((JSONObject)objects[0]).getLong("id");
        name = ((JSONObject)objects[0]).getString("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);

        //修改入参
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",8);
        jsonObject.put("name","lisi");
        objects[0] = jsonObject;
        id = ((JSONObject)objects[0]).getLong("id");
        name = ((JSONObject)objects[0]).getString("name");
        System.out.println("修改后id:"+id);
        System.out.println("修改后name:"+name);
        //id小于0则抛出非法id异常
        if(id < 0){
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return  joinPoint.proceed(objects);
    }
}
