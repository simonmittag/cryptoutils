package com.simonmittag;

import com.simonmittag.cryptoutils.SymmetricKeyCipherWrapper;
import com.simonmittag.cryptoutils.Decoder;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CryptoUtilsTest extends TestCase {

    /**
     * Test the encryption decryption util. Keys are 16 Bit
     */
    public void testEncryptDecrypt() {
        System.setProperty(SymmetricKeyCipherWrapper.SYSTEM_WIDE_SYMMETRIC_SECRET_KEY, "0000888800008888");
        System.setProperty(SymmetricKeyCipherWrapper.SYSTEM_WIDE_INIT_VECTOR, "1111222233334444");

        Decoder decoder = SymmetricKeyCipherWrapper.getInstance();

        assertFalse("Hello World".equals(decoder.encrypt("Hello World")));
        assertTrue("Hello World".equals(decoder.decrypt(decoder.encrypt("Hello World"))));
    }
}
