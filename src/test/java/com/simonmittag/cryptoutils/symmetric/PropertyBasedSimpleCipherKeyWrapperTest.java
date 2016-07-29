package com.simonmittag.cryptoutils.symmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test property wrapper
 */
public class PropertyBasedSimpleCipherKeyWrapperTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty(PropertyBasedCipherKeyWrapper.SYMMETRIC_SECRET_KEY, "0000888800008888");
        System.setProperty(PropertyBasedCipherKeyWrapper.INIT_VECTOR, "abcd222233334444");
    }

    /**
     * Test the encryption decryption util. Keys are 16 Bit
     */
    public void testEncryptDecrypt() {
        long before = System.currentTimeMillis();
        SimpleCipher cipher = new CipherFacade();
        long invocation = System.currentTimeMillis() - before;
        System.out.println("it took " + invocation + " ms to create the Decoder instance");

        String encrypted = cipher.encrypt("Hello World");
        assertFalse("Hello World".equals(encrypted));

        //make sure the re-init does not compromise the decryption. This proves it'll work on 2nd Vm
        cipher = null;
        cipher = new CipherFacade();
        assertTrue("Hello World".equals(cipher.decrypt(encrypted)));
    }

    public void testBigEncryptDecrypt() throws URISyntaxException, IOException {
        String content = new String(Files.readAllBytes(
                Paths.get(ClassLoader.getSystemResource("war_of_the_worlds.txt").toURI())
        ));
        SimpleCipher decoder = CipherFactory.getInstance();

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
