Cryptoutils
============

Provides an ultra simple wrapper around Java's Cryptography extension (JCE) and factory methods for AES 128 Bit symmetric key ciphers, and RSA 2048 Bit asymmetric key ciphers with encryption/decryption methods. See unit test cases for details.

Building and running
--------------------

Use maven to build:

    mvn clean package

Using the library from code
---------------------------

Simply put the jar in your classpath, generate keys, then use it like this:

    System.setProperty("ASYMMETRIC_PRIVATE_KEY", "BASE64_PRIVATE_KEY")
    System.setProperty("ASYMMETRIC_PUBLIC_KEY", "BASE64_PUBLIC_KEY")

    or

    System.setProperty("SYMMETRIC_SECRET_KEY", "16_BYTE_SYMMETRIC_KEY";
    System.setProperty("INIT_VECTOR", "16_BYTE_INIT_VECTOR";

    then

    SimpleCipher cipher = new CipherFacade();

    String encrypted = cipher.encrypt("Hello World");
    String decrypted = cipher.decrypt(encrypted);


License
-------

Cryptoutils uses the Apache license.