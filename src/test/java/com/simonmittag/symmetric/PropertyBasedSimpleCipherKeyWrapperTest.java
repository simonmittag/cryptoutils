package com.simonmittag.symmetric;

import com.simonmittag.cryptoutils.symmetric.CipherFactory;
import com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper;
import com.simonmittag.cryptoutils.symmetric.SimpleSymmetricCipher;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class PropertyBasedSimpleCipherKeyWrapperTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty(PropertyBasedCipherKeyWrapper.SYSTEM_WIDE_SYMMETRIC_SECRET_KEY, "0000888800008888");
        System.setProperty(PropertyBasedCipherKeyWrapper.SYSTEM_WIDE_INIT_VECTOR, "1111222233334444");
    }

    /**
     * Test the encryption decryption util. Keys are 16 Bit
     */
    public void testEncryptDecrypt() {
        long before = System.currentTimeMillis();
        SimpleSymmetricCipher cipher = CipherFactory.getInstance();
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to create the Decoder instance");

        assertFalse("Hello World".equals(cipher.encrypt("Hello World")));
        assertTrue("Hello World".equals(cipher.decrypt(cipher.encrypt("Hello World"))));

        assertTrue("a".equals(cipher.decrypt(cipher.encrypt("a"))));
    }

    public void testBigEncryptDecrypt() throws URISyntaxException, IOException {
        String content = new String(Files.readAllBytes(
                Paths.get(ClassLoader.getSystemResource("war_of_the_worlds.txt").toURI())
        ));
        SimpleSymmetricCipher decoder = CipherFactory.getInstance();

        long before = System.currentTimeMillis();
        String encrypted = decoder.encrypt(content);
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to encrypt war of the worlds, it is " + encrypted.getBytes().length + " bytes long");

        before = System.currentTimeMillis();
        String decrypted = decoder.decrypt(encrypted);
        invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to decrypt war of the worlds, it is " + decrypted.getBytes().length + " bytes long");

        assertEquals(decrypted, content);
    }
}
