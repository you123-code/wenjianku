package com.demo.testdatasource.utils;

import cn.hutool.core.util.ObjectUtil;
import com.demo.testdatasource.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 14:00
 */
@Component
public class PubTool {
    @Autowired
    RequestService requestService;
    public int selectNum(String id,Object c) {
        Class<?> aClass = c.getClass();
        Method[] m = aClass.getMethods();
        for (Method method : m) {
            System.out.println(method);
        }
        try {
            Method m1 = aClass.getMethod("getById", Serializable.class);
            System.out.println(m1);
             Object o = m1.invoke(c,id);
            if(!ObjectUtil.isEmpty(o)){
                return Consts.Num.ONE;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Consts.Num.ZERO;
    }
}
