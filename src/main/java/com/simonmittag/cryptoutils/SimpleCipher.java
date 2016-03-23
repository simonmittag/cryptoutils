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
     * @param raw
     * @return
     */
    public String encrypt(String raw);

    /**
     * Decrypt an encrypted String and return as raw
     * @param encrypted
     * @return
     */
    public String decrypt(String encrypted);
}
