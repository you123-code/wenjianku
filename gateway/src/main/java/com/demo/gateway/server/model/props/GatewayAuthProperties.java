package com.demo.gateway.server.model.props;

import com.demo.gateway.server.model.dto.GatewayToken;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 粤省事参数配置信息
 * 		广东省数字广东--粤省事小程序网关接入参数信息
 */

@Data
@Component
@ConfigurationProperties(prefix="gateway.gd")
@RefreshScope
public class GatewayAuthProperties {

	private int overTime = 120;

	//验证token地址
	private String tokenUri = "";

	//客户端信息获取地址
	private String clientUri = "";

	private Map<String, GatewayToken> tokens;

	public GatewayToken getToken(String key ){
		Assert.notNull( tokens, "网关校验配置为空" );
		return tokens.get( key );
	}


}
