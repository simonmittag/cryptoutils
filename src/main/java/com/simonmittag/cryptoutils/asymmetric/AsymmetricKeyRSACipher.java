/*
 * AsymmetricKeyRSACipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.symmetric.SimpleCipher;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author simonmittag
 * @since <version>
 */
public class AsymmetricKeyRSACipher implements SimpleCipher {
    protected static final String UTF_8 = "UTF-8";
    protected static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    protected static final String BC = "BC";

    Cipher cipher;
    RSAPublicKey publicKey;
    RSAPrivateKey privateKey;

    public AsymmetricKeyRSACipher(String publicKey, String privateKey) {
        try {
            this.publicKey = (RSAPublicKey) KeyHelper.deserializePublicKey(publicKey);
            this.privateKey = (RSAPrivateKey) KeyHelper.deserializePrivateKey(privateKey);
            this.cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING, BC);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String raw) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(raw.getBytes(UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decodeBase64(encrypted)), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setKey(String key) {
        //do nothing
    }

    public void setInitVector(String initVector) {
        //do nothing.
    }
}
