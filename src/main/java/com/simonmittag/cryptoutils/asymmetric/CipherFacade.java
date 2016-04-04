/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 * @since <version>
 */
public class CipherFacade implements SimpleCipher {
    protected SimpleAsymmetricCipher cipher;

    public CipherFacade() {
        this.cipher = CipherFactory.getInstance();
    }

    public String encrypt(String raw) {
        return this.cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return this.cipher.decrypt(encrypted);
    }
}