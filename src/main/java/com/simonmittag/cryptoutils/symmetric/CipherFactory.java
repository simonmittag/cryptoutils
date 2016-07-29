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
 * @author simonmittag
 */
public class CipherFactory {
    protected static final String SHA_1 = "SHA-1";

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

    protected static boolean uninitialized(String property) {
        return getEnvOrProperty(property) == null;
    }

    /**
     * Creates a too simple init vector that is based on day of month, but can be used predictably across machines.
     * @return
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
