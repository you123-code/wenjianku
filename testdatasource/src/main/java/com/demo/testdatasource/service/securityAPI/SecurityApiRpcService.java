package com.demo.testdatasource.service.securityAPI;

import com.demo.testdatasource.model.dto.ClientConfig;
import com.demo.testdatasource.service.utils.CryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 16:48
 */
@Component
@Slf4j
public class SecurityApiRpcService {
    @Autowired
    private  BankClientHelperService bankClientHelperService;


    public static final String SIGNATURE_HEADER = "X-FC-SIGNATURE";
    public static final String AES_KEY_HEADER = "X-FC-ENCRYPTKEY";
    public static final String HEADER_CLIENT_ID = "x-fct-client";

    /**
     * 根据本地私钥解密出aesKey,然后再aes解出请求参数明文
     * @param body
     * @param aesKeyEnCode
     * @param clientConfig
     * @return
     */
    public String resolveData(String body, String aesKeyEnCode, ClientConfig clientConfig) {
        PrivateKey privateKey = bankClientHelperService.getPrivateKey(clientConfig);
        log.info("response aes key crypt text=>{}", aesKeyEnCode);
        String aesKey = CryptUtil.RSA.decryptByPrivateKey(aesKeyEnCode, privateKey);
        Assert.notNull(aesKey, "response aes key can not resolve with private key");
        log.info("response aes key =>{}", aesKey);
        return CryptUtil.AES.decrypt(body, aesKey);
    }


    /**
     * 验签
     * @param body
     * @param signature
     * @param clientConfig
     */
    public void verifySignature(String body, String signature, com.demo.testdatasource.model.dto.ClientConfig clientConfig) {
        PublicKey publicKey = bankClientHelperService.getPublicKey(clientConfig);
        log.info("response signature =>{}", signature);
        byte[] dataForSign = (body + "&key=" + clientConfig.getClientSecret()).getBytes(StandardCharsets.UTF_8);
        log.info("signature text=>{}", body + "&key=" + clientConfig.getClientSecret());
        if (null == signature ||
                !CryptUtil.RSA.verify(dataForSign, publicKey, signature,
                        CryptUtil.Const.ALGORITHM_SIGNATURE_SHA256WithRSA)
        ) {
            log.error("signature error ");
            throw new IllegalArgumentException("response body signature valid error!");
        }
    }

    /**
     * 计算签名
     * @param body
     * @return
     * @throws Exception
     */
    public String sign(String body, com.demo.testdatasource.model.dto.ClientConfig client, PrivateKey privateKey) throws Exception {

        String signature = CryptUtil.RSA.sign(
                (body + "&key=" + client.getClientSecret()).getBytes(StandardCharsets.UTF_8), privateKey,
                CryptUtil.Const.ALGORITHM_SIGNATURE_SHA256WithRSA);
        log.info("request signature =>{}", signature);
        return signature;
    }

    public String encryptBody(String body, String aesKey) {
        //对明文进行加密，采用AES加密
        String encryptBody = CryptUtil.AES.encrypt(body, aesKey);
        log.info("request body encrypt text =>{}", encryptBody);
        return encryptBody;
    }
}
