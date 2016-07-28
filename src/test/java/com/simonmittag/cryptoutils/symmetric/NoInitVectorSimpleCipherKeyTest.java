package com.simonmittag.cryptoutils.symmetric;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Test
    public void testEncryptionPerformance() throws UnsupportedEncodingException {
        int base=1;
        do {
            printDecryptionPerformance(printEncryptionPerformance(base*=2));
        } while (base<Math.pow(2,26));
    }

    public String printEncryptionPerformance(int lengthInBytes) throws UnsupportedEncodingException {
        StringBuilder b = new StringBuilder();
        for(int i=0;i<lengthInBytes;i++) { b.append("a"); }
        String message = b.toString();
        SimpleSymmetricCipher cipher = CipherFactory.getInstance();
        long before = System.nanoTime();
        String encrypted = cipher.encrypt(message);
        long after = System.nanoTime();
        long elapsedNanos = after-before;
        System.out.printf("\nthe AES128 encryption of %s bytes took %s nanoseconds or %s microseconds or %s milliseconds)",
                message.length(), elapsedNanos, elapsedNanos/1000, elapsedNanos/1000000);
        return encrypted;
    }

    public String printDecryptionPerformance(String encrypted) throws UnsupportedEncodingException {
        SimpleSymmetricCipher cipher = CipherFactory.getInstance();
        long before = System.nanoTime();
        String decrypted = cipher.encrypt(encrypted);
        long after = System.nanoTime();
        long elapsedNanos = after-before;
        System.out.printf("and decryption of the same message took %s nanoseconds or %s microseconds or %s milliseconds)",
                elapsedNanos, elapsedNanos/1000, elapsedNanos/1000000);
        return encrypted;
    }

}
