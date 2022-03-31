package com.demo.gateway.server.model.dto;

import lombok.Data;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/31 18:19
 */
@Data
public class GatewayToken {
    private String passId;
    private String passToken;
    private String tokenUri;
    private String clientUri;
    private int overTime = 120;

}
