package com.demo.gateway.server.model.props;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConditionalOnExpression( "!'${ignore-path}'.isEmpty()" )
@ConfigurationProperties( prefix = "ignore-path" )
@RefreshScope
public class IgnorePathProperties {

    /**
     * 放行终端配置，网关不校验此处的终端
     */
    private List<String> clients = new ArrayList<>();

    /**
     * 放行url,放行的url不再被安全框架拦截
     */
    private List<String> urls = new ArrayList<>();

    /**
     * 不聚合swagger
     */
    private List<String> swaggerProviders =new ArrayList<>();

}