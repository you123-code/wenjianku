package com.demo.testdatasource.utils.selfinterface;

import java.lang.annotation.*;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/3 10:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnnotation {
}
