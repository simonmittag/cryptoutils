package com.simonmittag;

import com.simonmittag.cryptoutils.AESCipher;
import com.simonmittag.cryptoutils.CellBlock1138CipherWrapper;
import com.simonmittag.cryptoutils.Decoder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CryptoUtilsTest extends TestCase {

    /**
     * Test the encryption decryption util
     */
    public void testEncryptDecrypt() {
        System.setProperty(CellBlock1138CipherWrapper.SYSTEM_WIDE_SYMMETRIC_SECRET_KEY, "0000888800008888");
        System.setProperty(CellBlock1138CipherWrapper.SYSTEM_WIDE_INIT_VECTOR, "1111222233334444");

        Decoder decoder = new CellBlock1138CipherWrapper(new AESCipher());

        assertFalse("Hello World".equals(decoder.encrypt("Hello World")));
        assertTrue("Hello World".equals(decoder.decrypt(decoder.encrypt("Hello World"))));
    }
}
