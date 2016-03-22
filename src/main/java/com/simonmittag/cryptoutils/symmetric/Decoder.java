/**
 * Decoder
 */
package com.simonmittag.cryptoutils.symmetric;

/**
 * @author simonmittag
 * @since <version>
 */
public interface Decoder {

    public String encrypt(String raw);
    public String decrypt(String encrypted);
    public void setKey(String key);
    public void setInitVector(String initVector);
}
