/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 * @since <version>
 */
public class PropertyBasedCipherKeyWrapper implements SimpleCipher {
    public static final String ASYMMETRIC_PRIVATE_KEY = "ASYMMETRIC_PRIVATE_KEY";
    public static final String ASYMMETRIC_PUBLIC_KEY = "ASYMMETRIC_PUBLIC_KEY";

    protected SimpleAsymmetricCipher cipher;

    public PropertyBasedCipherKeyWrapper(SimpleAsymmetricCipher cipher) {
        this.cipher = cipher;
        this.cipher.setPublicKey(System.getProperty(ASYMMETRIC_PUBLIC_KEY));
        this.cipher.setPrivateKey(System.getProperty(ASYMMETRIC_PRIVATE_KEY));
    }

    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }
}
