package com.demo.gateway.server.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:52
 */
@Data
@Builder
public class ClientConfig {
    /**
     * 客户端唯一编码
     */
    private String clientId;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端密钥（apiKey）
     */
    private String clientSecret;

    /**
     * 客户端公钥证书路径
     */
    private String pubCer;

    /**
     * 本地私钥证书路径
     */
    private String PriCer;


    /**
     * 本地证书密码
     */
    private String keyCertPwd;

    /**
     * 本地证书别名
     */
    private String keyAliasName;

    /**
     * 证书库密码
     */
    private String keyStorePwd;

    /**
     * 连接方式（true长/false短）
     */
    private String httpKeepAlive;

    /**
     * 请求目标服务地址
     */
    private String apiRoot;

}
