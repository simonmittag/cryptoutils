/*
 * DecoderFactory
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.simonmittag.cryptoutils.ByteHelper.UTF_8;
import static com.simonmittag.cryptoutils.ByteHelper.getFill;
import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;
import static com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper.INIT_VECTOR;
import static com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper.SYMMETRIC_SECRET_KEY;

/**
 * Factory class for SimpleSymmetricCipher
 * @author simonmittag
 */
public class CipherFactory {
    /**
     * We use SHA-1
     */
    protected static final String SHA_1 = "SHA-1";

    /**
     * Create an instance of SimpleSymmetricCipher
     * @return an instance of SimpleSymmetricCipher
     */
    public static SimpleSymmetricCipher getInstance() {
        if (uninitialized(SYMMETRIC_SECRET_KEY)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set SYMMETRIC_SECRET_KEY as System property with a 16 Byte key?");
        }
        if (uninitialized(INIT_VECTOR)) {
            System.setProperty(INIT_VECTOR, getInitVector());
        }

        return new PropertyBasedCipherKeyWrapper(new SymmetricKeyAESCipher());
    }

    /**
     * Check if a property has been initialized
     * @param property An environment variable or java system property
     * @return true | false
     */
    protected static boolean uninitialized(String property) {
        return getEnvOrProperty(property) == null;
    }

    /**
     * Creates a very simple init vector that is based on day of month, but can be used predictably across
     * different instances for 24h
     * @return a timed init vector that is guaranteed for 24h
     */
    protected static String getInitVector() {
        try {
            byte[] initVector = (getFill()+"").getBytes(UTF_8);
            while(initVector.length<16) {
                initVector = digest(initVector+"");
            }
            return Base64.encodeBase64String(initVector);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] digest(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return MessageDigest.getInstance(SHA_1).digest(msg.getBytes(UTF_8));
    }
}
