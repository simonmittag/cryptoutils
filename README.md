Cryptoutils
============

Provides an ultra simple wrapper around Java's Cryptography extension (JCE) and factory methods for AES 128 Bit symmetric key ciphers, and RSA 2048 Bit asymmetric key ciphers with encryption/decryption methods. See unit test cases for details.

Building and running
--------------------

Use maven to build:

    mvn clean package

Using the library from code
---------------------------

1) Use the key generator to create public/private keys (for asymmetric cipher) and save them to disk.

2) Create a random 16 byte String each (for the symmetric cipher).

3) put cryptoutils-1.0-SNAPSHOT.jar in your classpath, then use it like so from within code, replacing the property values:

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

License
-------

Cryptoutils uses the Apache license.