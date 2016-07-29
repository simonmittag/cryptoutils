/*
 * AsymmetricKeyRSACipherTest
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @author simonmittag
 */
public class AsymmetricKeyRSACipherTest {
    protected String publicKey;
    protected String privateKey;
    protected SimpleCipher cipher;

    @Before
    public void setup() {
        publicKey = readClassPathResourceAsString("public_key");
        privateKey = readClassPathResourceAsString("private_key");
        cipher = new AsymmetricKeyRSACipher(publicKey, privateKey);
    }

    @Test
    public void testEncryptDecrypt() {
        String encrypted = cipher.encrypt("Hello World");
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(decrypted, "Hello World");
    }

    @Test
    public void testEncryptionPerformance() throws UnsupportedEncodingException {
        int base = 1;
        do {
            printDecryptionPerformance(printEncryptionPerformance(base *= 2));
        } while (base < 128);
        //curious that RSA can only encrypt keylength / 8 - Padding bytes
    }

    public String printEncryptionPerformance(int lengthInBytes) throws UnsupportedEncodingException {
        String message = "";
        for (int i = 0; i < lengthInBytes; i++) {
            message += "a";
        }
        long before = System.nanoTime();
        String encrypted = cipher.encrypt(message);
        long after = System.nanoTime();
        long elapsedNanos = after - before;
        System.out.printf("\nthe RSA 2048 encryption of %s bytes took %s nanoseconds or %s microseconds or milliseconds",
                message.length(), elapsedNanos, elapsedNanos / 1000, elapsedNanos / 1000000);
        return encrypted;
    }

    public String printDecryptionPerformance(String encrypted) throws UnsupportedEncodingException {
        long before = System.nanoTime();
        String decrypted = cipher.decrypt(encrypted);
        long after = System.nanoTime();
        long elapsedNanos = after - before;
        System.out.printf(" and decryption of the same message took %s nanoseconds or %s microseconds or milliseconds",
                elapsedNanos, elapsedNanos / 1000, elapsedNanos / 1000000);
        return decrypted;
    }


    protected String readClassPathResourceAsString(String relativePath) {
        try {
            String content = new String(Files.readAllBytes(
                    Paths.get(ClassLoader.getSystemResource(relativePath).toURI())
            ));
            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
