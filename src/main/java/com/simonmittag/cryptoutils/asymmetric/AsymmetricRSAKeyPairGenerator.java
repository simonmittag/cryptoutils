/*
 * AsymmetricRSAKeyPairGenerator
 */
package com.simonmittag.cryptoutils.asymmetric;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import static com.simonmittag.cryptoutils.asymmetric.KeyHelper.serializePrivateKey;
import static com.simonmittag.cryptoutils.asymmetric.KeyHelper.serializePublicKey;

/**
 * Generates a RSA 2048 bit private/public keypair
 * @author simonmittag
 */
public class AsymmetricRSAKeyPairGenerator {
    protected static final String RSA = "RSA";
    protected static final int KEY_LENGTH_BIT = 2048;
    protected static final String UTF_8 = "UTF-8";
    protected static File publicKeyFile;
    protected static File privateKeyFile;

    /**
     * Static initialiser
     */
    static {
        publicKeyFile = new File("public_key");
        privateKeyFile = new File("private_key");
    }

    /**
     * Runs with no args
     * @param args CLI args - empty
     * @throws IOException for IO exception
     * @throws GeneralSecurityException for GeneralSecurityException
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
        keyGen.initialize(KEY_LENGTH_BIT);
        KeyPair keyPair = keyGen.genKeyPair();

        writeKeyToFile(serializePublicKey(keyPair.getPublic()).getBytes(UTF_8), publicKeyFile);
        writeKeyToFile(serializePrivateKey(keyPair.getPrivate()).getBytes(UTF_8), privateKeyFile);
    }

    /**
     * Write the key bytes to the file system
     * @param bytes The key as byte[]
     * @param keyFile The output file
     * @throws IOException for IOException
     * @throws GeneralSecurityException for GeneralSecurityException
     */
    protected static void writeKeyToFile(byte[] bytes, File keyFile) throws IOException, GeneralSecurityException {
        FileOutputStream fos = new FileOutputStream(keyFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
}
