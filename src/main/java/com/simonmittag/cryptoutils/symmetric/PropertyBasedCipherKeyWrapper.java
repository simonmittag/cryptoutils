/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.symmetric;

/**
 * @author simonmittag
 * @since <version>
 */
public class PropertyBasedCipherKeyWrapper implements SimpleSymmetricCipher {
    public static final String SYSTEM_WIDE_SYMMETRIC_SECRET_KEY = "SYSTEM_WIDE_SYMMETRIC_SECRET_KEY";
    public static final String SYSTEM_WIDE_INIT_VECTOR = "SYSTEM_WIDE_INIT_VECTOR";

    protected SimpleSymmetricCipher cipher;

    public PropertyBasedCipherKeyWrapper(SimpleSymmetricCipher cipher) {
        this.cipher = cipher;
        this.setKey(System.getProperty(SYSTEM_WIDE_SYMMETRIC_SECRET_KEY));
        this.setInitVector(System.getProperty(SYSTEM_WIDE_INIT_VECTOR));
    }

    public void setKey(String key) {
        this.cipher.setKey(key);
    }

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
