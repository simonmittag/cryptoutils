/*
 * AESCipher
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author simonmittag
 * AES Cipher
 * UTF-8
 * @since <version>
 */
public class SymmetricKeyAESCipher implements SimpleSymmetricCipher {
    protected static final String UTF_8 = "UTF-8";
    protected static final String AES = "AES";
    protected static final String AES_CBC_PKCS5_PADDING = AES + "/CBC/PKCS5PADDING";

    protected static final String CRYPTO_ERROR = "crypto error, cause: ";

    protected String key = null;
    protected String initVector = null;

    public SymmetricKeyAESCipher() {
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setInitVector(String initVector) {
        this.initVector = initVector;
    }

    public String encrypt(String raw) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), 0, 16, AES);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(raw.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException(CRYPTO_ERROR + ex.getMessage());
        }
    }

    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original, UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(CRYPTO_ERROR + ex.getMessage());
        }
    }
}
