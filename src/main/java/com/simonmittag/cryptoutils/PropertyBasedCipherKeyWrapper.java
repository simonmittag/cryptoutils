/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils;

/**
 * @author simonmittag
 * @since <version>
 */
public class PropertyBasedCipherKeyWrapper implements Decoder {
    public static final String SYSTEM_WIDE_SYMMETRIC_SECRET_KEY = "SYSTEM_WIDE_SYMMETRIC_SECRET_KEY";
    public static final String SYSTEM_WIDE_INIT_VECTOR = "SYSTEM_WIDE_INIT_VECTOR";

    protected Decoder decoder;

    public PropertyBasedCipherKeyWrapper(Decoder decoder) {
        this.decoder = decoder;
        this.setKey(System.getProperty(SYSTEM_WIDE_SYMMETRIC_SECRET_KEY));
        this.setInitVector(System.getProperty(SYSTEM_WIDE_INIT_VECTOR));
    }

    public void setKey(String key) {
        this.decoder.setKey(key);
    }

    public void setInitVector(String initVector) {
        this.decoder.setInitVector(initVector);
    }

    public String encrypt(String raw) {
        return decoder.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return decoder.decrypt(encrypted);
    }

    public static Decoder getInstance() {
        return new PropertyBasedCipherKeyWrapper(new SymmetricKeyAESCipher());
    }
}
