package com.demo.testdatasource.service.utils;

import com.demo.testdatasource.model.dto.ClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:50
 */
@Slf4j
public class CryptUtil {
    public static class Const {

        static final String CHARSET = "UTF-8";

        /**
         * 加密算法RSA
         */
        static final String ALGORITHM_RSA = "RSA";
        static final String ALGORITHM_AES = "AES";
        static final String ALGORITHM_SHA256 = "SHA-256";

        /**
         * AES 加密补位算法
         */
        static final String AES_PADDING_MODE_ECB_PKCS5 = "AES/ECB/PKCS5Padding";
        static final String AES_PADDING_MODE_CBC_PKCS5 = "AES/CBC/PKCS5Padding";

        static final String SECURE_RANDOM_SHA1PRNG = "SHA1PRNG";
        static final String PROVIDER_SUN = "SUN";


        /**
         * RSA 加密补位算法
         */
        static String RSA_PADDING_MODE_OAEPWithSHA1AndMGF1 = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
        static String RSA_PADDING_MODE_PKCS1 = "RSA/ECB/PKCS1Padding";

        static final String RSA_PUBLIC_KEY = "RSAPublicKey";
        static final String RSA_PRIVATE_KEY = "RSAPrivateKey";

        /**
         * * 签名算法
         */
        static final String ALGORITHM_SIGNATURE_MD5WithRSA = "MD5withRSA";
        public static final String ALGORITHM_SIGNATURE_SHA256WithRSA = "SHA256withRSA";


    }

    public static class SHA256 {

        public static String getSHA256(String str) {
            MessageDigest messageDigest;
            String encodeStr = null;
            try {
                messageDigest = MessageDigest.getInstance(Const.ALGORITHM_SHA256);
                messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
                encodeStr = byte2Hex(messageDigest.digest());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encodeStr;
        }

        public static String byte2Hex(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            String temp = null;
            for (byte aByte : bytes) {
                temp = Integer.toHexString(aByte & 0xFF);
                if (temp.length() == 1) {
                    //1得到一位的进行补0操作
                    sb.append("0");
                }
                sb.append(temp);
            }
            return sb.toString();
        }
    }

    public static class AES {

        /**
         * 构建Cipher
         *
         * @param key        加密密钥
         * @param crypt_mode 加解密模式
         * @return Cipher
         * @throws Exception 构建异常
         */
        private static Cipher buildCipherWithCbc(String key, int crypt_mode) throws Exception {

            byte[] keyBte = key.getBytes(StandardCharsets.UTF_8);

//            SecureRandom secureRandom =
//                    SecureRandom.getInstance(Const.SECURE_RANDOM_SHA1PRNG,Const.PROVIDER_SUN);
//            secureRandom.setSeed(keyBte);
//
//            KeyGenerator keyGenerator = KeyGenerator.getInstance(Const.ALGORITHM_AES);
//            keyGenerator.init(128,secureRandom);
//            SecretKey secretKey = keyGenerator.generateKey();
//            keyBte  = secretKey.getEncoded();

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBte, Const.ALGORITHM_AES);
            byte[] iv = Arrays.copyOfRange(keyBte, 0, 16);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(Const.AES_PADDING_MODE_CBC_PKCS5);
            cipher.init(crypt_mode, secretKeySpec, ivParameterSpec);
            return cipher;
        }

        /**
         * AES 加密
         *
         * @param data 明文
         * @param key  密钥
         * @return 密文(base64)
         */
        public static String encrypt(String data, String key) {
            try {
                Cipher cipher = buildCipherWithCbc(key, Cipher.ENCRYPT_MODE);
                byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * AES 解密
         *
         * @param data 密文(base64)
         * @param key  密钥
         * @return 明文
         */
        public static String decrypt(String data, String key) {
            try {
                byte[] encrypted = Base64.decodeBase64(data);
                Cipher cipher = buildCipherWithCbc(key, Cipher.DECRYPT_MODE);
                byte[] original = cipher.doFinal(encrypted);
                return new String(original, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class RSA {

        /**
         * RSA 密钥对生成方法
         * { RSAPublicKey: object, RSAPrivateKey: object }
         *
         * @return Map 密钥对
         * @throws Exception 生成异常
         */
        static Map<String, Object> genKeyPair() throws Exception {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Const.ALGORITHM_RSA);
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = new HashMap<String, Object>(2);
            keyMap.put(Const.RSA_PUBLIC_KEY, publicKey);
            keyMap.put(Const.RSA_PRIVATE_KEY, privateKey);
            return keyMap;
        }

        /**
         * RSA 签名
         * 默认算法 MD5withRSA
         *
         * @param data     待签数据字节数组
         * @param privateK 私钥
         * @return 签名（base64）
         */
        public static String sign(byte[] data, PrivateKey privateK) {
            return sign(data, privateK, Const.ALGORITHM_SIGNATURE_MD5WithRSA);
        }

        /**
         * RSA 签名
         *
         * @param data                待签数据字节数组
         * @param privateK            私钥
         * @param algorithm_signature 签名算法
         * @return 签名（base64）
         */
        public static String sign(byte[] data, PrivateKey privateK, String algorithm_signature) {
            try {
                Signature signature = Signature.getInstance(algorithm_signature);
                signature.initSign(privateK);
                signature.update(data);
                return Base64.encodeBase64String(signature.sign());
            } catch (Exception e) {
                throw new RuntimeException("signature build error", e);
            }

        }

        /**
         * RSA 验签
         * 验签算法 MD5withRSA
         *
         * @param data    待验证数据字节数组
         * @param publicK 公钥
         * @param sign    签名（base64字符串）
         * @return true/false
         */
        public static boolean verify(byte[] data, PublicKey publicK, String sign) {
            return verify(data, publicK, sign, Const.ALGORITHM_SIGNATURE_MD5WithRSA);
        }

        /**
         * RSA 验签
         * 验签算法 MD5withRSA
         *
         * @param data                待验证数据字节数组
         * @param publicK             公钥
         * @param sign                签名（base64字符串）
         * @param algorithm_signature 验签算法
         * @return true/false
         */
        public static boolean verify(byte[] data, PublicKey publicK, String sign, String algorithm_signature) {
            try {
                Signature signature = Signature.getInstance(algorithm_signature);
                signature.initVerify(publicK);
                signature.update(data);
                return signature.verify(Base64.decodeBase64(sign.getBytes()));
            } catch (Exception e) {
                log.error("verify error", e);
                return false;
            }
        }

        /**
         * 公钥加密
         *
         * @param plainText 明文
         * @param publicKey 公钥
         * @return 加密密文
         */
        public static String encryptByPublicKey(String plainText, PublicKey publicKey) {
            String result = null;
            try {
                Cipher cipher = Cipher.getInstance(Const.RSA_PADDING_MODE_OAEPWithSHA1AndMGF1);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] encoded = cipher.doFinal(plainText.getBytes(Const.CHARSET));
                result = Base64.encodeBase64String(encoded);
            } catch (Exception e) {
                log.error("encryptByPublicKey error:", e);
            }
            return result;
        }

        /**
         * 对称密钥密文解密
         *
         * @param privateKey 私钥
         * @param content    密文(base64)
         * @return 对称密钥明文
         */
        public static String decryptByPrivateKey(String content, PrivateKey privateKey) {
            String result = null;
            try {
                Cipher cipher = Cipher.getInstance(Const.RSA_PADDING_MODE_OAEPWithSHA1AndMGF1);
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] encoded = cipher.doFinal(Base64.decodeBase64(content));
                result = new String(encoded, Const.CHARSET);
            } catch (Exception e) {
                log.error("decryptByPrivateKey error:", e);
            }
            return result;
        }
    }
    /**
     * 内置加解密配置数据（临时）
     * @param clientId 客户端id
     * @return
     */
    public static ClientConfig getClientConfig(String clientId) {
        List<com.demo.testdatasource.model.dto.ClientConfig> list = new ArrayList<com.demo.testdatasource.model.dto.ClientConfig>() {{
            add(com.demo.testdatasource.model.dto.ClientConfig.builder()
                    .clientId("FsFoshanBOG")
                    .clientName("(贷款业务)")
                    .clientSecret("FsFoshanBOG")
                    //银行公钥
                    //.pubCer("classpath:security/bank/fs-bog.cer")
                    //测试用本地公钥
                    .pubCer("classpath:security/server/localhost.cer")
                    //本地公钥
                    .PriCer("classpath:security/server/localhost.p12")
                    .keyCertPwd("123456")
                    .keyAliasName("localhost")
                    .keyStorePwd("123456")
                    .httpKeepAlive("false")
                    .apiRoot("http://19.129.103.12:8081").build());
        }};
        com.demo.testdatasource.model.dto.ClientConfig collect = list.stream().filter(c -> c.getClientId().equals(clientId)).collect(Collectors.toList()).get(0);

        return collect;
    }
}
