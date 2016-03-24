/**
 * SimpleAsymmetricCipher
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 * @since <version>
 */
public interface SimpleAsymmetricCipher extends SimpleCipher {
    void setPublicKey(String publicKey);

    void setPrivateKey(String privateKey);
}
