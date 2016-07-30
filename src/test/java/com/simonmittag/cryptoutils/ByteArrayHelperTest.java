/*
 * ByteMeTest
 */
package com.simonmittag.cryptoutils;

import static com.simonmittag.cryptoutils.ByteArrayHelper.*;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * String to byte conversion tests
 * @author simonmittag
 */
public class ByteArrayHelperTest extends TestCase {

    public void testByteArrayIsAlways16Bytes() throws UnsupportedEncodingException {
        assertTrue(generateSixteenByteLongArray(Base64.getEncoder().encodeToString("1111222233334444".getBytes())).length==16);
        assertTrue(generateSixteenByteLongArray("1111222233334444").length==16);
        assertTrue(generateSixteenByteLongArray("1111222233334444555555555").length==16);
        assertTrue(generateSixteenByteLongArray("111122223333").length==16);
        assertTrue(generateSixteenByteLongArray("1").length==16);
        assertTrue(generateSixteenByteLongArray("").length==16);
    }
}
