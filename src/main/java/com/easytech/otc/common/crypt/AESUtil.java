package com.easytech.otc.common.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES加解密工具类
 */
@Slf4j
public class AESUtil {

    private static final String UTF_8 = "UTF-8";

    private AESUtil() {
    }

    private static class Holder {
        private static final AESUtil INSTANCE = new AESUtil();
    }

    public static AESUtil getInstance() {
        return Holder.INSTANCE;
    }

    public static String encrypt(String data, String sKey, String ivParameter) {
        try {
            return Holder.INSTANCE.encrypt0(data, sKey, ivParameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String data, String sKey, String ivParameter) {
        try {
            return Holder.INSTANCE.decrypt0(data, sKey, ivParameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt0(String encData, String secretKey, String vector) throws Exception {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    public String decrypt0(String sSrc, String key, String ivs) throws Exception {
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }
}