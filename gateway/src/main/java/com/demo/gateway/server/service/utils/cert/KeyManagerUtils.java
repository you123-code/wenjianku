package com.demo.gateway.server.service.utils.cert;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/30 17:41
 */
public class KeyManagerUtils {
    private static KeyStore getKeyStore(
            String clientPrivateCertPath,
            String clientPrivateCertPassword)
            throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        InputStream inputStream = ResUtils.getResInputStream(clientPrivateCertPath);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(inputStream, clientPrivateCertPassword.toCharArray());
        return keyStore;
    }

    private static X509ExtendedKeyManager getX509ExtendedKeyManager(
            String clientPrivateCertPath,
            String clientPrivateCertPassword)
            throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        KeyStore keyStore = getKeyStore(clientPrivateCertPath,clientPrivateCertPassword);
        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, clientPrivateCertPassword.toCharArray());
        KeyManager[] keyManagers = keyFactory.getKeyManagers();
        for (KeyManager keyManager : keyManagers) {
            if (keyManager instanceof X509KeyManager) {
                if (keyManager instanceof X509ExtendedKeyManager) {
                    return (X509ExtendedKeyManager) keyManager;
                }
            }
        }
        return null;
    }

    public static PrivateKey getPrivateKey(
            String certP12Path,
            String certP12Password,
            String certAliasName
    ) {
        try {
            X509ExtendedKeyManager keyManager = getX509ExtendedKeyManager(certP12Path, certP12Password);
            assert (keyManager != null);
            return keyManager.getPrivateKey(certAliasName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Certificate getCert(
            String certP12Path,
            String certP12Password,
            String certAliasName
    ) {
        try {
            KeyStore keyStore = getKeyStore(certP12Path, certP12Password);
            return keyStore.getCertificate(certAliasName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
