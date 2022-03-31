package com.demo.gateway.server.service.client;

import com.demo.gateway.server.model.dto.ClientConfig;

public abstract class AbstractClientDetailService {

    public abstract boolean support( String source );

    public abstract ClientConfig clientConfig(String clientId, String source );

}
