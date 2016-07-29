/**
 * SimpleAsymmetricCipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * SimpleAsymmetricCipher interface
 * @author simonmittag
 */
public interface SimpleAsymmetricCipher extends SimpleCipher {
    /**
     * Constant ASYMMETRIC_PRIVATE_KEY
     */
    public static final String ASYMMETRIC_PRIVATE_KEY = "ASYMMETRIC_PRIVATE_KEY";

    /**
     * Constant ASYMMETRIC_PUBLIC_KEY
     */
    public static final String ASYMMETRIC_PUBLIC_KEY = "ASYMMETRIC_PUBLIC_KEY";

    /**
     * Set the public key
     * @param publicKey base64 encoded public key
     */
    void setPublicKey(String publicKey);

    /**
     * Set the private key
     * @param privateKey base64 encoded private key
     */
    void setPrivateKey(String privateKey);
}
