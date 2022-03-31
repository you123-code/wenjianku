package com.demo.gateway.config.certs;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.MD5;
import com.demo.gateway.service.utils.KeyManagerUtils;
import com.demo.gateway.service.utils.ResUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:48
 */
@Component
@Slf4j
public class KeyPairManageService {
    private static final Map<String, PublicKey> publicKeysMap = MapUtil.newConcurrentHashMap( 8 );
    private static final Map<String, PrivateKey> privateKeyMap = MapUtil.newConcurrentHashMap( 8 );


    public PublicKey getPublicKeyByCerPath( String cerPath , String subjectId ){
        String key = genKey( subjectId + cerPath );
        PublicKey publicKey  = publicKeysMap.get( key );
        if( publicKey != null ){
            return publicKey;
        }
        try (InputStream fis = ResUtils.getResInputStream(cerPath)) {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificatefactory.generateCertificate(fis);
            Assert.notNull(certificate,"can not find cer ,path => "+cerPath);
            publicKey = certificate.getPublicKey();
            Assert.notNull( publicKey, "can not get public key from cer =>"+cerPath);
            publicKeysMap.put( key,  publicKey);
            return publicKey;
        }catch ( Exception e ){
            throw new RuntimeException(
                    String.format("public key init error ,subjectId:%s,cerPath:%s",subjectId,cerPath), e );
        }
    }


    public PrivateKey getPrivateKeyByPath( String filePath,
                                           String keyStorePwd,String aliasName,String keyPwd, String subjectId ){
        String key = genKey( subjectId + filePath + aliasName );
        PrivateKey privateKey  = privateKeyMap.get( key );
        if( privateKey != null ){
            return privateKey;
        }
        try{
            privateKey = KeyManagerUtils.getPrivateKey( filePath, keyStorePwd, aliasName );
            Assert.notNull(privateKey,"can not get Private Key ,path => " + filePath);
            privateKeyMap.put( key,  privateKey);
            return privateKey;
        }catch ( Exception e ){
            throw new RuntimeException(
                    String.format("private key init error ,subjectId:%s,cerPath:%s,aliasName:%s",
                            subjectId,filePath,aliasName), e );
        }
    }

    private String genKey( String data ){
        return MD5.create().digestHex(data,"utf-8").toLowerCase();
    }

}
