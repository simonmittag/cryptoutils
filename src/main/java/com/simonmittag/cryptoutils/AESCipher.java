/*
 * AESCipher
 */
package com.simonmittag.cryptoutils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author simonmittag
 * @since <version>
 */
public class AESCipher implements Decoder {
    protected static final String UTF_8 = "UTF-8";
    protected static final String AES = "AES";
    protected static final String AES_CBC_PKCS5_PADDING = AES + "/CBC/PKCS5PADDING";

    protected String key = null;
    protected String initVector = null;

    public AESCipher() {
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
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(raw.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException("error during encryption, cause: " + ex.getMessage());
        }
    }

    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original, UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("error during decryption, cause: " + ex.getMessage());
        }
    }
}
