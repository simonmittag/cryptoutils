package com.simonmittag;

import com.simonmittag.cryptoutils.symmetric.DecoderFactory;
import com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper;
import com.simonmittag.cryptoutils.symmetric.Decoder;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CryptoUtilsTest extends TestCase {

    /**
     * Test the encryption decryption util. Keys are 16 Bit
     */
    public void testEncryptDecrypt() {
        System.setProperty(PropertyBasedCipherKeyWrapper.SYSTEM_WIDE_SYMMETRIC_SECRET_KEY, "0000888800008888");
        System.setProperty(PropertyBasedCipherKeyWrapper.SYSTEM_WIDE_INIT_VECTOR, "1111222233334444");

        long before = System.currentTimeMillis();
        Decoder decoder = DecoderFactory.getInstance();
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to create the Decoder instance");

        assertFalse("Hello World".equals(decoder.encrypt("Hello World")));
        assertTrue("Hello World".equals(decoder.decrypt(decoder.encrypt("Hello World"))));

        assertTrue("a".equals(decoder.decrypt(decoder.encrypt("a"))));
    }
}
