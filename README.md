Cryptoutils
============

Provides an ultra simple wrapper around Java's Cryptography extension (JCE) and factory methods for AES 128 Bit symmetric key ciphers, and RSA 2048 Bit asymmetric key ciphers with encryption/decryption methods. See unit test cases for details.

Building from source
--------------------

Use maven to build:

    mvn clean package

Import
------

Declare as dependency in maven

    <dependency>
      <groupId>com.simonmittag</groupId>
      <artifactId>cryptoutils</artifactId>
      <version>1.0</version>
    </dependency>

Using the library from code
---------------------------

1) Use AsymmetricRSAKeyPairGenerator to create public/private keys (for asymmetric cipher) and save them to disk.

    mvn -q exec:java -Dexec.mainClass="com.simonmittag.cryptoutils.asymmetric.AsymmetricRSAKeyPairGenerator"

2) Create a random 16 byte String each for symmetric secret key and init vector (for the symmetric cipher).

3) put cryptoutils-1.0-SNAPSHOT.jar in your classpath, then use it like so from within code, replacing the property values with the base64 (UTF-8 Strings are ok for symmetric) encoded keys you generated in #1 and #2:

    System.setProperty("ASYMMETRIC_PRIVATE_KEY", "BASE64_PRIVATE_KEY")
    System.setProperty("ASYMMETRIC_PUBLIC_KEY", "BASE64_PUBLIC_KEY")

    or

    System.setProperty("SYMMETRIC_SECRET_KEY", "16_BYTE_SYMMETRIC_KEY");
    System.setProperty("INIT_VECTOR", "16_BYTE_INIT_VECTOR");

3b) alternatively define the above properties as unix environment variables on the shell before starting the Java process that contains the client code.

4) Create a cipher by using the Facade class in either package like so:

    SimpleCipher cipher = new CipherFacade();

    String encrypted = cipher.encrypt("Hello World");
    String decrypted = cipher.decrypt(encrypted);


Known Issues
------------

Cryptoutils was tested on Oracle JDK8+ with the built-in JCE provider. Other environments may vary


License
-------

Cryptoutils uses the Apache license.
