package com.demo.suanfa.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/2/2 17:34
 */
@Configuration
@ComponentScan("com.demo.suanfa.*")
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
