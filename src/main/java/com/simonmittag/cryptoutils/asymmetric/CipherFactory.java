/*
 * CipherFactory
 */
package com.simonmittag.cryptoutils.asymmetric;

import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;


/**
 * @author simonmittag
 */
public class CipherFactory {
    public static SimpleAsymmetricCipher getInstance() {
        if(uninitialized(SimpleAsymmetricCipher.ASYMMETRIC_PRIVATE_KEY)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set ASYMMETRIC_PRIVATE_KEY as System property with a 16 Byte key?");
        }
        if(uninitialized(SimpleAsymmetricCipher.ASYMMETRIC_PUBLIC_KEY)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set ASYMMETRIC_PUBLIC_KEY as System property with a 16 Byte value?");
        }

        return new PropertyBasedCipherKeyWrapper(new AsymmetricKeyRSACipher());
    }

    public static boolean uninitialized(String property) {
        return getEnvOrProperty(property)==null;
    }
}
