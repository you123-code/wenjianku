package com.demo.gateway.server.service.client;

import com.demo.gateway.server.model.dto.ClientConfig;

public interface ClientService {

    ClientConfig clientDetail(String clientId, String source);

}
