package com.demo.testdatasource.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/3 11:44
 */
@Aspect
@Component
@Slf4j
public class LogAspectHandler {
    @Pointcut("execution(* com.demo.testdatasource..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("====doBefore方法进入了====");

        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //HttpServletRequest request = attributes.getRequest();
        // 获取请求 URL
        //String url = request.getRequestURL().toString();
        // 获取请求 IP
        //String ip = request.getRemoteAddr();
        //log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }
}
