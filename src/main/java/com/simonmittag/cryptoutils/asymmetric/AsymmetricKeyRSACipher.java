/*
 * AsymmetricKeyRSACipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import com.simonmittag.cryptoutils.symmetric.SimpleSymmetricCipher;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import static com.simonmittag.cryptoutils.asymmetric.KeyHelper.*;

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
            this.publicKey = (RSAPublicKey) deserializePublicKey(publicKey);
            this.privateKey = (RSAPrivateKey) deserializePrivateKey(privateKey);
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
}
