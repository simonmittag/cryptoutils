/*
 * CipherFacade
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.asymmetric.*;

/**
 * @author simonmittag
 * @since <version>
 */
public class CipherFacade implements SimpleAsymmetricCipher {
    protected SimpleAsymmetricCipher cipher;

    public CipherFacade() {
        this.cipher = CipherFactory.getInstance();
    }

    public void setPublicKey(String publicKey) {
        //do nothing this is handled by the property wrapper
    }

    public void setPrivateKey(String privateKey) {
        //do nothing this is handled by the property wrapper
    }

    public String encrypt(String raw) {
        return this.cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return this.cipher.decrypt(encrypted);
    }
}