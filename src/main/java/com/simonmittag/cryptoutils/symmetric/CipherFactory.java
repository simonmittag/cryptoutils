/*
 * DecoderFactory
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.simonmittag.cryptoutils.ByteArrayHelper.UTF_8;
import static com.simonmittag.cryptoutils.ByteArrayHelper.getFillerByte;
import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;
import static com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper.INIT_VECTOR;
import static com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper.SYMMETRIC_SECRET_KEY;

/**
 * Factory class for SimpleSymmetricCipher. Can create a symmetric cipher only with secret key
 * and provide init vector. This is not secure and only recommended for testing.
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
     * Creates a very simple init vector if one wasn't supplied for convenience.
     * Do not recommend this in production, always choose a good INIT_VECTOR for initialisation.
     * @return an init vector
     */
    protected static String getInitVector() {
        try {
            byte[] initVector = new byte[]{getFillerByte()};
            while(initVector.length<16) {
                initVector = digest(new String(initVector));
            }
            initVector = Arrays.copyOf(initVector,16);
            return Base64.encodeBase64String(initVector);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a SHA-1 digest
     * @param msg the message to encode
     * @return the SHA-1 digest as a byte[]
     * @throws NoSuchAlgorithmException for ingestion error
     * @throws UnsupportedEncodingException for ingestion error
     */
    private static byte[] digest(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return MessageDigest.getInstance(SHA_1).digest(msg.getBytes(UTF_8));
    }
}
