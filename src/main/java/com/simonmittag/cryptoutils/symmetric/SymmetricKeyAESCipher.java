/*
 * AESCipher
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import static com.simonmittag.cryptoutils.ByteHelper.UTF_8;
import static com.simonmittag.cryptoutils.ByteHelper.byteMe;

/**
 * @author simonmittag
 * AES128 Symmetric Cipher for UTF-8 encoded Strings
 */
public class SymmetricKeyAESCipher implements SimpleSymmetricCipher {
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
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIvParameterSpec());
            byte[] encrypted = cipher.doFinal(raw.getBytes(UTF_8));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException(CRYPTO_ERROR + ex.getMessage());
        }
    }

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

    protected SecretKeySpec getSecretKeySpec() throws UnsupportedEncodingException {
        return new SecretKeySpec(byteMe(key), 0, 16, AES);
    }

    protected IvParameterSpec getIvParameterSpec() throws UnsupportedEncodingException {
        return new IvParameterSpec(byteMe(initVector));
    }
}
