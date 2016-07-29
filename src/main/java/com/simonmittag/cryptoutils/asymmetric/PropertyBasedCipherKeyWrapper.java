/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;

/**
 * @author simonmittag
 */
public class PropertyBasedCipherKeyWrapper implements SimpleAsymmetricCipher {
    protected SimpleAsymmetricCipher cipher;

    public PropertyBasedCipherKeyWrapper(SimpleAsymmetricCipher cipher) {
        this.cipher = cipher;
        this.cipher.setPublicKey(getEnvOrProperty(SimpleAsymmetricCipher.ASYMMETRIC_PUBLIC_KEY));
        this.cipher.setPrivateKey(getEnvOrProperty(SimpleAsymmetricCipher.ASYMMETRIC_PRIVATE_KEY));
    }

    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }

    public void setPublicKey(String publicKey) {
        this.cipher.setPublicKey(publicKey);
    }

    public void setPrivateKey(String privateKey) {
        this.cipher.setPrivateKey(privateKey);
    }
}
