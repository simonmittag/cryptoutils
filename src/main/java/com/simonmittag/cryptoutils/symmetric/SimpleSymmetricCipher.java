/**
 * Decoder
 */
package com.simonmittag.cryptoutils.symmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * Utility wrapper interface for Java JCE symmetric ciphers.
 * @author simonmittag
 */
public interface SimpleSymmetricCipher extends SimpleCipher {

    /**
     * Set the symmetric key as String of length 16 byte
     * @param key The symmetric key
     */
    public void setKey(String key);


    /**
     * Set the initVecor key as String of length 16 byte
     * @param initVector The init vector
     */
    public void setInitVector(String initVector);
}
