/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * Facade class, representing a Singleton instance of SimpleAsymmetricCipher
 * @author simonmittag
 */
public class CipherFacade implements SimpleCipher {

    /**
     * The default cipher
     */
    protected SimpleAsymmetricCipher cipher;

    /**
     * Creates an instance via the default factory
     */
    public CipherFacade() {
        this.cipher = CipherFactory.getInstance();
    }

    /**
     * Delegates to SimpleAsymmetricCipher
     * @param raw The raw String message to encrypt
     * @return The encrypted String
     */
    public String encrypt(String raw) {
        return this.cipher.encrypt(raw);
    }

    /**
     * Delegates to SimpleAsymmetricCipher
     * @param encrypted The encrypted String message to decrypt
     * @return The decrypted String
     */
    public String decrypt(String encrypted) {
        return this.cipher.decrypt(encrypted);
    }
}