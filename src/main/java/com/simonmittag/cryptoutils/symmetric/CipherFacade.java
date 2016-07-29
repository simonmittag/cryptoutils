/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.symmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * Facade class, representing a Singleton instance of SimpleCipher
 * @author simonmittag
 */
public class CipherFacade implements SimpleCipher {

    /**
     * Delegate to this cipher
     */
    protected SimpleSymmetricCipher cipher;

    /**
     * Create instance with this delegate
     */
    public CipherFacade() {
        this.cipher = CipherFactory.getInstance();
    }

    /**
     * Encrypt a raw String and return the encrypted result as String
     * @param raw The raw String message to encrypt
     * @return The encrypted message
     */
    public String encrypt(String raw) {
        return this.cipher.encrypt(raw);
    }

    /**
     * Decrypt an encrypted String and return as raw
     * @param encrypted The encrypted String message to decrypt
     * @return The decrypted message
     */
    public String decrypt(String encrypted) {
        return this.cipher.decrypt(encrypted);
    }
}
