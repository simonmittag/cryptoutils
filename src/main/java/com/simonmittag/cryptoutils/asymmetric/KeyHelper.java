/*
 * KeyHelper
 */
package com.simonmittag.cryptoutils.asymmetric;

import org.apache.commons.codec.binary.Base64;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @author simonmittag
 */
public class KeyHelper {
    protected static String RSA = "RSA";

    public static PrivateKey deserializePrivateKey(String key64) throws GeneralSecurityException {
        byte[] clear = Base64.decodeBase64(key64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }


    public static PublicKey deserializePublicKey(String stored) throws GeneralSecurityException {
        byte[] data = Base64.decodeBase64(stored);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        return fact.generatePublic(spec);
    }

    public static String serializePrivateKey(PrivateKey priv) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec spec = fact.getKeySpec(priv,
                PKCS8EncodedKeySpec.class);
        byte[] packed = spec.getEncoded();
        String key64 = Base64.encodeBase64String(packed);
        Arrays.fill(packed, (byte) 0);
        return key64;
    }


    public static String serializePublicKey(PublicKey publ) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec spec = fact.getKeySpec(publ,
                X509EncodedKeySpec.class);
        return Base64.encodeBase64String(spec.getEncoded());
    }

}
