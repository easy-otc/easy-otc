package com.easytech.otc.common.crypt;

import com.google.common.hash.Hashing;

public class CryptUtil {

    public static String md5(String input) {
        return Hashing.md5().hashBytes(input.getBytes()).toString();
    }

    public static String sha256(String input) {
        return Hashing.sha256().hashBytes(input.getBytes()).toString();
    }
}