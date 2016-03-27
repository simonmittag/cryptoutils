Cryptoutils
============

Provides an ultra simple wrapper around Java's Cryptography extension (JCE) and factory methods for AES 128 Bit symmetric key ciphers, and RSA 2048 Bit asymmetric key ciphers with encryption/decryption methods. See unit test cases for details.

Building and running
--------------------

Use maven to build:

    mvn clean package

Using the library from code
---------------------------

Simply put the jar in your classpath, generate keys in Java, then use it like this:

    SimpleSymmetricCipher cipher = CipherFactory.getInstance();

    or

    SimpleCipher cipher = new AsymmetricKeyRSACipher(publicKey, privateKey);
    

    then 

    String encrypted = cipher.encrypt("Hello World");
    String decrypted = cipher.decrypt(encrypted);


License
-------

Loggingutils uses the Apache license. 