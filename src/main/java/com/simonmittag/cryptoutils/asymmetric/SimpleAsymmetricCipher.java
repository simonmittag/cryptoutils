/**
 * SimpleAsymmetricCipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 */
public interface SimpleAsymmetricCipher extends SimpleCipher {
    public static final String ASYMMETRIC_PRIVATE_KEY = "ASYMMETRIC_PRIVATE_KEY";
    public static final String ASYMMETRIC_PUBLIC_KEY = "ASYMMETRIC_PUBLIC_KEY";

    void setPublicKey(String publicKey);

    void setPrivateKey(String privateKey);
}
