package com.demo.gateway;

import cn.hutool.core.util.RandomUtil;
import com.demo.gateway.model.dto.ClientConfig;
import com.demo.gateway.service.securityAPI.BankClientHelperService;
import com.demo.gateway.service.securityAPI.SecurityApiRpcService;
import com.demo.gateway.service.utils.CryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.security.PublicKey;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class GatewayApplicationTests {

    @Autowired
    private SecurityApiRpcService securityApiRpcService;
    @Resource
    private BankClientHelperService bankClientHelperService;


    @Test
    void test() throws Exception {
         /*
        //测试验签
        String body = "{\"debtorIdNumber\":\"310101199805066547\",\"applicationNumber\":\"gfg214512\",\"projectType\":\"2\",\"closeReason\":\"736786\"}";
        String signature = "d3RUmQwShb8yUokYytWWUT5FuQlpr3VcOpCGZB02NFEPN1RlFfqhDH/oGcNS8+53kzn5//r6nHZfk5M06AaofeBM5WptiB3abIuxBytOcwit4U8iyh7af2vfwvKvYhsVYqAfvf6RchVrEK6w1SSN2lPaBG+hzUEbC4iaS3a30CVAHkPNPcRY1LMKDdNkbTp9/UskC+80eeobxUbJSBztwR+uRwc+hAK0klG5+T2/tdTUWd6MIItuZr0kiMkxRNny41gxHyd+sBkw9YM2jStUVOu1OQtM2V93y1S6JgHVffiwtvM2REx1RanQzCzCWlIFnsj/Kmm0bwRGgpb1QWQXWw==";
        ClientConfig clientConfig = CryptUtil.getClientConfig("FsFoshanBOG");
        PrivateKey privateKey = bankClientHelperService.getPrivateKey(clientConfig);
        String sign = securityApiRpcService.sign(body,clientConfig,privateKey);
        securityApiRpcService.verifySignature( body,  sign,  clientConfig);
        System.out.println("验签结束");
        */
        /**
         * 测试流程
         */
        String body = "{\"debtorIdNumber\":\"310101199805066547\",\"applicationNumber\":\"gfg214512\",\"projectType\":\"2\",\"closeReason\":\"736786\"}";
        ClientConfig clientConfig = CryptUtil.getClientConfig("FsFoshanBOG");
        PrivateKey privateKey = bankClientHelperService.getPrivateKey(clientConfig);
        PublicKey publicKey = bankClientHelperService.getPublicKey(clientConfig);
        //本地，使用RSA+银行公钥加密AES秘钥
        String aesKey = RandomUtil.randomString( 32 );
        String enKey = CryptUtil.RSA.encryptByPublicKey(aesKey,publicKey);
        //本地：使用AES秘钥加密body
        String enbody = CryptUtil.AES.encrypt(body,aesKey);
        //String enbody = "XfqdTdGS4uqjn5ZYxvkM7gUxWGl7os2likeRAk6pkk08yHHOPwOZdDflZKl0yAj3Zn7oZX8Tl6ibBjSOwn5ckkENe+0oMMMbFvakVCKNADU=";
        //String enKey = "gGMiRICrlg0X1HQVb9op0pHMDD/oanePR1bU32EQjihpY8NgZ/YzKPL4TyNiJGuGlcdaaTLBhsillz2oFUmLPAbAaxFolnviFae+xjWNG4Zw7N0phrX6Wjq1BTse5suEHsl6BnSqzXALmXhfdpZG2jo7MiM3vmwdPHw82xU6wUlLw8KFw4uIcH5ovaJQtkuYRIdgOrs3Hsjex3GQz5f0+YI443JB08Tq3Gb3nDOeRtQQWncFRwKbkrzw3osoNdO6HoFHMIAPPBLwdNEl85DL9WEZKFmZfYjueH6xkP3tujBgQZXo+jS7SH20L0XgX49JhPk9XsEGW9eY5RLRXSe2kQ==";
        String desKey = CryptUtil.RSA.decryptByPrivateKey(enKey, privateKey);
        //String desKey = "000000000000000056428db2007c2d6c";
        System.out.println(desKey);
        String decryptBody = CryptUtil.AES.decrypt(enbody, desKey);
        System.out.println("解密后body"+decryptBody);
        //本地：使用本地私钥加签
        String sign = securityApiRpcService.sign(body,clientConfig,privateKey);
        System.out.printf("%s%n%s%n%s%n",enKey,enbody,sign);
        //银行，使用RSA+银行私钥解密AES秘钥
        String deaesKey = CryptUtil.RSA.decryptByPrivateKey(enKey,privateKey);
        //银行：使用AES秘钥对body解密获取明文
        String debody = CryptUtil.AES.decrypt(enbody,deaesKey);
        //银行，本地公钥验签
        securityApiRpcService.verifySignature( debody,  sign,  clientConfig);
        System.out.printf("%s%n%s%n%s%n",aesKey,body,sign);
        System.out.printf("%s%n%s%n%s%n",deaesKey,debody,sign);
        System.out.println("本地到银行验签结束");
        //银行，使用本地公钥验签通过
        //业务处理后的业务数据body1
        String body1 = "aaaa";
        //银行，使用RSA+本地公钥加密AES秘钥
        String aesKey1 = RandomUtil.randomString( 32 );
        String enaesKey1 = CryptUtil.RSA.encryptByPublicKey(aesKey1,publicKey);
        //银行，使用AES秘钥加密返回body
        String enbody1 = CryptUtil.AES.encrypt(body1,aesKey1);
        //银行，银行私钥加签
        String sign1 = securityApiRpcService.sign(body1,clientConfig,privateKey);
        //本地，使用RSA+本地私钥解密得到AES秘钥
        String deaesKey1 = CryptUtil.RSA.decryptByPrivateKey(enaesKey1,privateKey);
        //本地，使用AES秘钥解密body
        String debody1 = CryptUtil.AES.decrypt(enbody1,deaesKey1);
        //本地，使用银行公钥验签
        securityApiRpcService.verifySignature(debody1,sign1,clientConfig);
        System.out.printf("%s%n%s%n%s%n",aesKey1,body1,sign1);
        System.out.printf("%s%n%s%n%s%n",deaesKey1,debody1,sign1);
        System.out.println("流程结束");
        //结束
    }
}
