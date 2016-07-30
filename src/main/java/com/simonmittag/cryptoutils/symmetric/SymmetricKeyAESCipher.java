/*
 * AESCipher
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import static com.simonmittag.cryptoutils.ByteArrayHelper.UTF_8;
import static com.simonmittag.cryptoutils.ByteArrayHelper.generateSixteenByteLongArray;

/**
 * AES128 Symmetric Cipher for UTF-8 encoded Strings
 * @author simonmittag
 */
public class SymmetricKeyAESCipher implements SimpleSymmetricCipher {

    /**
     * AES
     */
    protected static final String AES = "AES";

    /**
     * We use AES with PKCS5PADDING
     */
    protected static final String AES_CBC_PKCS5_PADDING = AES + "/CBC/PKCS5PADDING";

    /**
     * Sorry mate, it didn't work
     */
    protected static final String CRYPTO_ERROR = "crypto error, cause: ";

    /**
     * Symmetric key as String
     */
    protected String key = null;

    /**
     * Init vector as String
     */
    protected String initVector = null;

    /**
     * Creates an instance of SimpleSymmetricCipher with AES
     */
    public SymmetricKeyAESCipher() {
    }

    /**
     * Sets the symmetric key as base64 encoded String
     * @param key The symmetric key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets the init vector as base64 encoded String
     * @param initVector The init vector
     */
    public void setInitVector(String initVector) {
        this.initVector = initVector;
    }

    /**
     * Encrypt a raw String and return the encrypted result as String
     * @param raw The raw String message to encrypt
     * @return The encrypted message
     */
    public String encrypt(String raw) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIvParameterSpec());
            byte[] encrypted = cipher.doFinal(raw.getBytes(UTF_8));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException(CRYPTO_ERROR + ex.getMessage());
        }
    }

    /**
     * Decrypt an encrypted String and return as raw
     * @param encrypted The encrypted String message to decrypt
     * @return The decrypted message
     */
    public String decrypt(String encrypted) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, getSecretKeySpec(), getIvParameterSpec());
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original, UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(CRYPTO_ERROR + ex.getMessage());
        }
    }

    /**
     * Get SecretKeySpec
     * @return SecretKeySpec
     * @throws UnsupportedEncodingException for key errors
     */
    protected SecretKeySpec getSecretKeySpec() throws UnsupportedEncodingException {
        return new SecretKeySpec(generateSixteenByteLongArray(key), 0, 16, AES);
    }

    /**
     * Get IvParameterSpec
     * @return IvParameterSpec
     * @throws UnsupportedEncodingException for key errors
     */
    protected IvParameterSpec getIvParameterSpec() throws UnsupportedEncodingException {
        return new IvParameterSpec(generateSixteenByteLongArray(initVector));
    }
}
