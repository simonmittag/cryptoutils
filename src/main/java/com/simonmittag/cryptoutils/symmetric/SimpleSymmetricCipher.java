/**
 * Decoder
 */
package com.simonmittag.cryptoutils.symmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 * Utility wrapper interface for Java JCE symmetric ciphers.
 */
public interface SimpleSymmetricCipher extends SimpleCipher {

    /**
     * Set the symmetric key as String
     * @param key The symmetric key
     */
    public void setKey(String key);


    /**
     * Set the asymmetric key as String
     * @param initVector The init vector
     */
    public void setInitVector(String initVector);
}
