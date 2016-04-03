package com.simonmittag.cryptoutils.symmetric;

import junit.framework.TestCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class NoInitVectorSimpleCipherKeyTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty(PropertyBasedCipherKeyWrapper.SYMMETRIC_SECRET_KEY, "0000888800008888");
    }

    /**
     * Test the encryption decryption util. Keys are 16 Bit
     */
    public void testEncryptDecrypt() {
        long before = System.currentTimeMillis();
        SimpleSymmetricCipher cipher = CipherFactory.getInstance();
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to create the Decoder instance");

        String encrypted = cipher.encrypt("Hello World");
        assertFalse("Hello World".equals(encrypted));

        //make sure the re-init does not compromise the decryption. This proves it'll work on 2nd Vm
        cipher = null;
        cipher = CipherFactory.getInstance();
        assertTrue("Hello World".equals(cipher.decrypt(encrypted)));
    }
}
