/*
 * DecoderFactory
 */
package com.simonmittag.cryptoutils.symmetric;

import static com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper.*;

/**
 * @author simonmittag
 * @since <version>
 */
public class CipherFactory {

    public static SimpleSymmetricCipher getInstance() {
        if(uninitialized(SYMMETRIC_SECRET_KEY)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set SYSTEM_WIDE_SYMMETRIC_SECRET_KEY as System property with a 16 Byte key?");
        }
        if(uninitialized(INIT_VECTOR)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set SYSTEM_WIDE_INIT_VECTOR as System property with a 16 Byte value?");
        }

        return new PropertyBasedCipherKeyWrapper(new SymmetricKeyAESCipher());
    }

    protected static boolean uninitialized(String property) {
        return "".equals(System.getProperty(property, ""));
    }
}
