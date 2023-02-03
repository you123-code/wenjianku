package com.demo.suanfa.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/2 15:30
 */
@Component
@Aspect
public class DataAspect {

    @Pointcut("execution(* com.demo.suanfa..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void advice(){
        System.out.println("advice  log");
    }

    @After("pointCut()")
    public void adviceAfter(){
        System.out.println("advice  log");
    }
}
