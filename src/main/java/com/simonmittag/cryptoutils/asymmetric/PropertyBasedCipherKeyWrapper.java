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

    protected SimpleCipher cipher;

    public PropertyBasedCipherKeyWrapper(SimpleCipher cipher) {
        this.cipher = cipher;
    }

    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }
}
