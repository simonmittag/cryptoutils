/*
 * DecoderFactory
 */
package com.simonmittag.cryptoutils;

import static com.simonmittag.cryptoutils.PropertyBasedCipherKeyWrapper.*;

/**
 * @author simonmittag
 * @since <version>
 */
public class DecoderFactory {
    public static Decoder getInstance() {
        if(uninitialized(SYSTEM_WIDE_SYMMETRIC_SECRET_KEY)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set SYSTEM_WIDE_SYMMETRIC_SECRET_KEY as System property with a 16 Byte key?");
        }
        if(uninitialized(SYSTEM_WIDE_INIT_VECTOR)) {
            throw new RuntimeException(
                    "DecoderFactory not initialized, have you set SYSTEM_WIDE_INIT_VECTOR as System property with a 16 Byte value?");
        }

        return new PropertyBasedCipherKeyWrapper(new SymmetricKeyAESCipher());
    }

    protected static boolean uninitialized(String property) {
        return "".equals(System.getProperty(property, ""));
    }
}
