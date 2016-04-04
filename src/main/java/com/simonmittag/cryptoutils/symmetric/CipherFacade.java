/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.symmetric;

/**
 * @author simonmittag
 * @since <version>
 */
public class CipherFacade implements SimpleSymmetricCipher {
    protected SimpleSymmetricCipher cipher;

    public CipherFacade() {
        this.cipher = CipherFactory.getInstance();
    }

    public void setKey(String key) {
        //do nothing this is handled by the property wrapper
    }

    public void setInitVector(String initVector) {
        //do nothing this is handled by the property wrapper
    }

    public String encrypt(String raw) {
        return this.cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return this.cipher.decrypt(encrypted);
    }
}
