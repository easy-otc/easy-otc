package com.easytech.otc.common;

import com.easytech.otc.common.crypt.CryptUtil;
import org.apache.commons.lang3.StringUtils;

public class PasswdUtil {

    public static String encode(String passwd) {

        for (int i = 0; i < 99; ++i) {

            if ((i & 1) == 1) {
                passwd = CryptUtil.md5(passwd + "_" + i);
                continue;
            }

            passwd = CryptUtil.sha256(passwd + "_" + i);
        }

        return passwd;
    }

    public static boolean verify(String passwd, String encodedPasswd) {
        return StringUtils.equals(encode(passwd), encodedPasswd);
    }
}