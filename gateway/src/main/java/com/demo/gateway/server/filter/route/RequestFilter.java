package com.demo.gateway.server.filter.route;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/29 10:37
 */
public class RequestFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        //return ((exchange, chain) -> );
        return null;
    }
}
