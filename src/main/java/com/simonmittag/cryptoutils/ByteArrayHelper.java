/*
 * ByteHelper
 */
package com.simonmittag.cryptoutils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Turn a fixed length String into a byte[] to use as symmetric key or init vector.
 * Fills Strings that are too short with zeroed bytes. Crops Strings that are too long.
 * Note this class converts to byte[] for convenience, not with security front of mind. You are
 * recommended to use good 16 byte Strings as key input material.
 */
public class ByteArrayHelper {

    /**
     * Constant for UTF-8 encoding
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * Use this to get a 16 byte array out of any String that is *either* UTF-8 or BASE64 encoded.
     * @param encoded The encoded String, should be UTF-8 String or can be Base64 encoded.
     * @return a semi-random byte[16], based on the input String.
     * @throws UnsupportedEncodingException if an unsupported String format is used
     */
    public static byte[] generateSixteenByteLongArray(String encoded) throws UnsupportedEncodingException {
        byte [] bytes = encoded.getBytes(UTF_8);
        if(bytes.length==16) {
            return bytes;
        } else {
            bytes = Base64.decodeBase64(encoded);
            if(bytes.length==16) {
                return bytes;
            } else {
                if(bytes.length>16) {
                    return Arrays.copyOf(bytes, 16);
                } else {
                    int offset = bytes.length;
                    byte[] expanded = new byte[16];
                    System.arraycopy(bytes, 0, expanded, 0, offset);
                    Arrays.fill(expanded, offset, 16, getFillerByte());
                    return expanded;
                }
            }
        }
    }

    /**
     * Uses ascii zeroes as filler bytes
     * @return an integer
     */
    public static byte getFillerByte() {
        return (byte)0l;
    }
}
