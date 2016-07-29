/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;

/**
 * Wrapper class for System env or java property based initialisation
 * @author simonmittag
 */
public class PropertyBasedCipherKeyWrapper implements SimpleAsymmetricCipher {

    /**
     * Delegate to this cipher
     */
    protected SimpleAsymmetricCipher cipher;

    /**
     * Wrap this cipher
     * @param cipher the simple cipher
     */
    public PropertyBasedCipherKeyWrapper(SimpleAsymmetricCipher cipher) {
        this.cipher = cipher;
        this.cipher.setPublicKey(getEnvOrProperty(SimpleAsymmetricCipher.ASYMMETRIC_PUBLIC_KEY));
        this.cipher.setPrivateKey(getEnvOrProperty(SimpleAsymmetricCipher.ASYMMETRIC_PRIVATE_KEY));
    }

    /**
     * Encrypt a raw String and return the encrypted result as String
     * @param raw The raw String message to encrypt
     * @return The encrypted message
     */
    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    /**
     * Decrypt an encrypted String and return as raw
     * @param encrypted The encrypted String message to decrypt
     * @return The decrypted message
     */
    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }

    /**
     * Set the public key
     * @param publicKey base64 encoded public key
     */
    public void setPublicKey(String publicKey) {
        this.cipher.setPublicKey(publicKey);
    }

    /**
     * Set the private key
     * @param privateKey base64 encoded private key
     */
    public void setPrivateKey(String privateKey) {
        this.cipher.setPrivateKey(privateKey);
    }
}
