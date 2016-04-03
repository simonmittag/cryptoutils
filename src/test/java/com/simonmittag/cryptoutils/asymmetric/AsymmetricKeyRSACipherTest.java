/*
 * AsymmetricKeyRSACipherTest
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import junit.framework.TestCase;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author simonmittag
 * @since <version>
 */
public class AsymmetricKeyRSACipherTest extends TestCase {
    public void testEncryptDecrypt() {
        String publicKey = readClassPathResourceAsString("public_key");
        String privateKey = readClassPathResourceAsString("private_key");
        SimpleCipher cipher = new AsymmetricKeyRSACipher(publicKey, privateKey);
        String encrypted = cipher.encrypt("Hello World");
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(decrypted, "Hello World");
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