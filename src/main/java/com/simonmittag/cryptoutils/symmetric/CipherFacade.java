/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.symmetric;

import com.simonmittag.cryptoutils.SimpleCipher;

/**
 * @author simonmittag
 */
public class CipherFacade implements SimpleCipher {
    protected SimpleSymmetricCipher cipher;

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
