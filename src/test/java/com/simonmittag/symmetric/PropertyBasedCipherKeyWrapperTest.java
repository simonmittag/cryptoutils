package com.simonmittag.symmetric;

import com.simonmittag.cryptoutils.symmetric.DecoderFactory;
import com.simonmittag.cryptoutils.symmetric.PropertyBasedCipherKeyWrapper;
import com.simonmittag.cryptoutils.symmetric.Decoder;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class PropertyBasedCipherKeyWrapperTest extends TestCase {

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
        Decoder decoder = DecoderFactory.getInstance();
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to create the Decoder instance");

        assertFalse("Hello World".equals(decoder.encrypt("Hello World")));
        assertTrue("Hello World".equals(decoder.decrypt(decoder.encrypt("Hello World"))));

        assertTrue("a".equals(decoder.decrypt(decoder.encrypt("a"))));
    }

    public void testBigEncryptDecrypt() throws URISyntaxException, IOException {
        String content = new String(Files.readAllBytes(
                Paths.get(ClassLoader.getSystemResource("war_of_the_worlds.txt").toURI())
        ));
        Decoder decoder = DecoderFactory.getInstance();

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
