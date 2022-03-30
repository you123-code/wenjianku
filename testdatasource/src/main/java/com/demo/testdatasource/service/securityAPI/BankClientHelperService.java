package com.demo.testdatasource.service.securityAPI;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:04
 */

import com.demo.testdatasource.config.certs.KeyPairManageService;
import com.demo.testdatasource.model.dto.ClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 证书管理类
 */
@Component
@Slf4j
public class BankClientHelperService {
    @Autowired
    private KeyPairManageService keyPairManageService;


    /**
     * 获取银行公钥
     * @param config
     * @return
     */
    public PublicKey getPublicKey( ClientConfig config ){
        return keyPairManageService.getPublicKeyByCerPath( config.getPubCer(), config.getClientId() );
    }


    /**
     * 获取本地公钥，测试用
     * @param config
     * @return
     */
//    @Deprecated
//    public PublicKey getFctPublicKey( ClientConfig config ){
//        return keyPairManageService.getPublicKeyByCerPath( config.getFctPubCer(), config.getClientId() );
//    }


    /**
     * 获取本地的私钥
     * @param config
     * @return
     */
    public PrivateKey getPrivateKey( com.demo.testdatasource.model.dto.ClientConfig config ){

        return keyPairManageService.getPrivateKeyByPath(
                config.getPriCer(), config.getKeyStorePwd(),
                config.getKeyAliasName(), config.getKeyCertPwd(),
                config.getClientId() );

    }


    /**
     * 模拟获取银行私钥，测试用
     * @param config
     * @return
     */
//    @Deprecated
//    public PrivateKey getBankPrivateKey( ClientConfig config ){
//
//        return keyPairManageService.getPrivateKeyByPath(
//                "/Users/xuhuaqiang/Documents/localhost.p12", config.getPassword(),
//                "bank", config.getPassword(),
//                "123456789" );
//
//    }

}
