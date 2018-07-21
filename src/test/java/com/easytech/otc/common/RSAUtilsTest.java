package com.easytech.otc.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.easytech.otc.common.crypt.RSAUtils;

public class RSAUtilsTest {

    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() {
        RSAUtils.K k = RSAUtils.initKey();
        publicKey = k.getPublicKey();
        privateKey = k.getPrivateKey();
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] encodedData = RSAUtils.encryptByPublicKey(inputStr, publicKey);
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
        assertTrue(status);
    }
}
