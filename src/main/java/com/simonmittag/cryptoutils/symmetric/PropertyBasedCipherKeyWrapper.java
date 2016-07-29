/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.symmetric;

import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;

/**
 * Wrapper class for System env or java property based initialisation
 * @author simonmittag
 */
public class PropertyBasedCipherKeyWrapper implements SimpleSymmetricCipher {

    /**
     * Constant for SYMMETRIC_SECRET_KEY
     */
    public static final String SYMMETRIC_SECRET_KEY = "SYMMETRIC_SECRET_KEY";

    /**
     * Constant for INIT_VECTOR
     */
    public static final String INIT_VECTOR = "INIT_VECTOR";

    /**
     * Cipher to delegate to
     */
    protected SimpleSymmetricCipher cipher;

    /**
     * Creates instance to delegate to
     * @param cipher
     */
    public PropertyBasedCipherKeyWrapper(SimpleSymmetricCipher cipher) {
        this.cipher = cipher;
        this.setKey(getEnvOrProperty(SYMMETRIC_SECRET_KEY));
        this.setInitVector(getEnvOrProperty(INIT_VECTOR));
    }

    /**
     * Set the symmetric Key
     * @param symmetricKey
     */
    public void setKey(String symmetricKey) {
        this.cipher.setKey(symmetricKey);
    }

    /**
     * Set the init vector
     * @param initVector The init vector
     */
    public void setInitVector(String initVector) {
        this.cipher.setInitVector(initVector);
    }


    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }
}
