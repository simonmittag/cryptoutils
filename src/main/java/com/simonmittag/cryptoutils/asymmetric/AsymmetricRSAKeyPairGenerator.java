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
 * @author simonmittag
 * Generates 2048 Bit key length public/private RSA key pair
 */
public class AsymmetricRSAKeyPairGenerator {
    protected static final String RSA = "RSA";
    protected static final int KEY_LENGTH_BIT = 2048;
    protected static final String UTF_8 = "UTF-8";
    protected static File publicKeyFile;
    protected static File privateKeyFile;

    static {
        publicKeyFile = new File("public_key");
        privateKeyFile = new File("private_key");
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
        keyGen.initialize(KEY_LENGTH_BIT);
        KeyPair keyPair = keyGen.genKeyPair();

        writeKeyToFile(serializePublicKey(keyPair.getPublic()).getBytes(UTF_8), publicKeyFile);
        writeKeyToFile(serializePrivateKey(keyPair.getPrivate()).getBytes(UTF_8), privateKeyFile);
    }

    protected static void writeKeyToFile(byte[] bytes, File keyFile) throws IOException, GeneralSecurityException {
        FileOutputStream fos = new FileOutputStream(keyFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
}
