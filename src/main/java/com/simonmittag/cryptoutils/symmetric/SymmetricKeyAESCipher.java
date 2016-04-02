/*
 * AESCipher
 */
package com.simonmittag.cryptoutils.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

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

    /**
     * Use this to get a 16 byte array out of a String that is *either* UTF-8 or BASE64 encoded.
     * @param encoded
     * @return
     * @throws UnsupportedEncodingException
     */
    protected byte[] byteMe(String encoded) throws UnsupportedEncodingException {
        byte [] bytes = encoded.getBytes(UTF_8);
        if(bytes.length==16) {
            return bytes;
        } else {
            bytes = Base64.decodeBase64(encoded);
            if(bytes.length==16) {
                return bytes;
            } else {
                throw new RuntimeException("uh oh, i can't convert your key or init vector to a 16 byte array");
            }
        }
    }
}
