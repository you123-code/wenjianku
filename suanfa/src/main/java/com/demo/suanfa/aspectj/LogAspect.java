package com.demo.suanfa.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/2 18:16
 */
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.demo.suanfa..*.*(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void advice(){
        System.out.println("日志打印。。。。。。。。。。。");
    }
}
