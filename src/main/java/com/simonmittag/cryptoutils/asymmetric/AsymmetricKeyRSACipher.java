/*
 * AsymmetricKeyRSACipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static com.simonmittag.cryptoutils.asymmetric.KeyHelper.deserializePrivateKey;
import static com.simonmittag.cryptoutils.asymmetric.KeyHelper.deserializePublicKey;

/**
 * Default Asymmetric cipher with RSA/ECB/PKCS1Padding settings for UTF-8 Strings.
 * @author simonmittag
 */
public class AsymmetricKeyRSACipher implements SimpleAsymmetricCipher {
    /**
     * Default text encoding
     */
    protected static final String UTF_8 = "UTF-8";

    /**
     * Default cipher suite using RSA encryption with PKCS1 padding
     */
    protected static String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * The JCE cipher used for encryption / decryption
     */
    protected Cipher cipher;

    /**
     * RSA public key
     */
    protected RSAPublicKey publicKey;

    /**
     * RSA private key
     */
    protected RSAPrivateKey privateKey;

    /**
     * Creates a default cipher instance
     */
    public AsymmetricKeyRSACipher() {
        try {
            this.cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a default cipher instance iwth a base64 encoded private and public keypair
     * @param publicKey a base64 encoded public key
     * @param privateKey a base64 encoded private key
     */
    public AsymmetricKeyRSACipher(String publicKey, String privateKey) {
        try {
            this.publicKey = (RSAPublicKey) deserializePublicKey(publicKey);
            this.privateKey = (RSAPrivateKey) deserializePrivateKey(privateKey);
            this.cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set the public key
     * @param publicKey base64 encoded public key
     */
    public void setPublicKey(String publicKey) {
        try {
            this.publicKey = (RSAPublicKey) deserializePublicKey(publicKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set the private key
     * @param privateKey base64 encoded private key
     */
    public void setPrivateKey(String privateKey) {
        try {
            this.privateKey = (RSAPrivateKey) deserializePrivateKey(privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypts a raw String
     * @param raw The raw String message to encrypt
     * @return The encrypted String
     */
    public String encrypt(String raw) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(raw.getBytes(UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Decrypts an encrypted String
     * @param encrypted The encrypted String message to decrypt
     * @return A decrypted String
     */
    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decodeBase64(encrypted)), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
