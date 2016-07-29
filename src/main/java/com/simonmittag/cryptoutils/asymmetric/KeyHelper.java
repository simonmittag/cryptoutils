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
 * Convenience methods for JCE key serialisation and deserialisation
 *
 * @author simonmittag
 */
public class KeyHelper {
    /**
     * We use RSA
     */
    protected static String RSA = "RSA";

    /**
     * Creates a JCE private key from base64 encoded string
     *
     * @param key64 base64 encoded String
     * @return a JCE private key object
     * @throws GeneralSecurityException for key creation exceptions
     */
    public static PrivateKey deserializePrivateKey(String key64) throws GeneralSecurityException {
        byte[] clear = Base64.decodeBase64(key64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }

    /**
     * Creates a JCE public key from base64 encoded string
     *
     * @param publicBase64 the base64 encoded String
     * @return JCE public key
     * @throws GeneralSecurityException for key creation exceptions
     */
    public static PublicKey deserializePublicKey(String publicBase64) throws GeneralSecurityException {
        byte[] data = Base64.decodeBase64(publicBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance(RSA);
        return fact.generatePublic(spec);
    }

    /**
     * Serialise JCE private key to base64 encoded String
     * @param privateKey JCE private key
     * @return A base64 encoded String
     * @throws GeneralSecurityException for encoding errors
     */
    public static String serializePrivateKey(PrivateKey privateKey) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec spec = fact.getKeySpec(privateKey, PKCS8EncodedKeySpec.class);
        byte[] packed = spec.getEncoded();
        String key64 = Base64.encodeBase64String(packed);
        Arrays.fill(packed, (byte) 0);
        return key64;
    }

    /**
     * Serialise JCE public key to base64 encoded String
     * @param publicKey JCE private key
     * @return A base64 encoded String
     * @throws GeneralSecurityException for encoding errors
     */
    public static String serializePublicKey(PublicKey publicKey
    ) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec spec = fact.getKeySpec(publicKey, X509EncodedKeySpec.class);
        return Base64.encodeBase64String(spec.getEncoded());
    }

}
