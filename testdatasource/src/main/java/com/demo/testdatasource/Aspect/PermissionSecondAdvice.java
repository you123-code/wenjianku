package com.demo.testdatasource.Aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/3 10:19
 * 同一个注解，多个切面，可以设置优先级
 */
//@Aspect
//@Component
//@Order(0)
public class PermissionSecondAdvice {
    @Pointcut("@annotation(com.demo.testdatasource.utils.selfinterface.PermissionAnnotation)")
    public void permissionCheck(){}

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("第二个切面"+System.currentTimeMillis());
        //获取请求参数
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject)objects[0]).getLong("id");
        String name = ((JSONObject)objects[0]).getString("name");
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        //id小于0则抛出非法id异常
        if(id < 0){
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return  joinPoint.proceed();
    }
}
