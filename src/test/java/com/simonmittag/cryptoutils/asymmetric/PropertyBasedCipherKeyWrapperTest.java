/*
 * PropertyBasedCipherKeyWrapperTest
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import junit.framework.TestCase;

/**
 * Test Property wrapper
 * @author simonmittag
 */
public class PropertyBasedCipherKeyWrapperTest extends TestCase{
    public static final String TEST_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjd0y/K5/QxIhv3kdIbbCvNc0jXRgEa8wnV1V8vaZ1eqJtoGiImQ8XjK1j0cjfN4HyqWOBzsnjCrYTom6TsuiyQho7S6Jd5MMtuKCEmgFb8bbhL3KiT14yHCbuFqeYbpkCSYkYCQ0lEtVPNx6OFT8gp/ExjnGWcGvgPt5N1p71/ijsNh/Yfe5bT3yCW4fIyZt9vHFcj9uoKpEygE/IxZQ+vl5tlicR/zwpieskDrVFMyWd3GRWpcWJ6WgzVZZSM2TtxPTiZWTmZP3XPFXd18vbdqTpVniexWMez0owaryBAPrgqfX5mRov+BNwQrdMzHN2iEkCVEJr+8TIRv00niOhQIDAQAB";
    public static final String TEST_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCN3TL8rn9DEiG/eR0htsK81zSNdGARrzCdXVXy9pnV6om2gaIiZDxeMrWPRyN83gfKpY4HOyeMKthOibpOy6LJCGjtLol3kwy24oISaAVvxtuEvcqJPXjIcJu4Wp5humQJJiRgJDSUS1U83Ho4VPyCn8TGOcZZwa+A+3k3WnvX+KOw2H9h97ltPfIJbh8jJm328cVyP26gqkTKAT8jFlD6+Xm2WJxH/PCmJ6yQOtUUzJZ3cZFalxYnpaDNVllIzZO3E9OJlZOZk/dc8Vd3Xy9t2pOlWeJ7FYx7PSjBqvIEA+uCp9fmZGi/4E3BCt0zMc3aISQJUQmv7xMhG/TSeI6FAgMBAAECggEAHXrxJIdSlOqIrtSJVD85eqqyFg8wA5zHWnxR5xqN32FM2utXeRQhY2XgSvT6j/FH5zm+KQyG7F7e8ns2hzdUDbi8rUU1de7N/pdVdmO9dXv6lDwogZqPpf+YrJfQP/jO3wPZHU2Iy6RYAzzB/UDT9IUlDBhnerGAOiqIBxO7lqaaTz/UKf4yv90vVIb/hGaOSh0K/XqxU1GoIN2F7bV2fJTsHHsMsHtmpilw1mkUGX3ZHJJC0aDwb5kh7ZEAi95oySuFL5uBEl1lQ3+XfW7J/7QoGkh3jeK/iQUlyI4BoY7LVX3JAbfz15m3/3czC/EtAs04IZdY4qcFpU5rM7+gAQKBgQDOkpZ6s7zgBSm1tDBAWIWQYet/o0qGyaYoh4T1D8PmM2j4L6SSpmkzLs6MRU8mOQtJsnR4cgomBwiMoDivzZtWkSDOShN7HJLj8woIy5aL31jxoHZueHjGWMjOuUrwkqRYxQWmJKGUQaaSXgm7SJov67y/uO4eArbABaUH2IQ4AQKBgQCvzvMZfhVQ4X3pB2VhnO5MHcS0s9aSDIg/LNs8/PvCDe8LDue+5uslGpAqIF19rI5d4stLxqBCp/ZH39dzvgV4YucwuUhgEh046o4FP5moCjohuC9XPmacclR+d6inUXKUS6nasn+ZuPAJEoeUaGq/rDiwgL4nUgR1FimaW/d2hQKBgQCWc4VN0OLbdmcuhBIwi0icI/VVfSSmju+JzUo1Akp3uzHUsT534OP19z1QRJqlCB8EigjyQHzLvYuogHlI8cMVhZYTKTiPDfyeMUE5rq3zKbSqJ2iahPvR2pBGoAXIucUhMj4vB7b3N+mN6RfFY9eI13UdXh9vlCJuMK5FzJhgAQKBgGUX1lghVTaGA3XdB+ICcFCaiYLzKn4e8LWLIKJIQqhKhNtR/dabSDm0tZNTuw7KC69W+1meZIcPuCGKSITIjrK0BIbS9BgyMlWJIpNICB5FiznahHZhbnWllIqIWhWkUwONIqWcIJ2PZ49/9TBUfpWq/YoY7b8EfYWqcc/N9FVVAoGBAMv21hcStI848UyXzukolxomLbfZr+PdLQfaNBvtgb2xTOVy19TdaHErJ40evKPH3t+ZEOLzHwfcssWzqdhrfRaUmRoBojJJPZFgw9pJLDOO7QmcUPmpEuThd+OzMCI5wBzB39TqddamiH2/DDDcae1fZLtCjhxOuT7xCw21UYf7";

    protected SimpleCipher cipher;

    public void setUp(){
        System.setProperty(SimpleAsymmetricCipher.ASYMMETRIC_PUBLIC_KEY,
                TEST_PUBLIC_KEY);
        System.setProperty(SimpleAsymmetricCipher.ASYMMETRIC_PRIVATE_KEY,
                TEST_PRIVATE_KEY);
        cipher = new CipherFacade();
    }

    public void testEncryptDecrypt() {
        String encryptedString = cipher.encrypt("Hello World");
        assertEquals("Hello World",cipher.decrypt(encryptedString));

    }
}
