/**
 * SimpleCipher
 */
package com.simonmittag.cryptoutils;

/**
 * @author simonmittag
 * Utility wrapper interface for Java JCE ciphers
 */
public interface SimpleCipher {

    /**
     * Encrypt a raw String and return the encrypted result as String
     * @param raw The raw String message to encrypt
     * @return The encrypted message
     */
    public String encrypt(String raw);

    /**
     * Decrypt an encrypted String and return as raw
     * @param encrypted The encrypted String message to decrypt
     * @return The decrypted message
     */
    public String decrypt(String encrypted);
}
