package com.easytech.otc.common;

import org.junit.Test;

public class PasswdUtilTest {

    @Test
    public void testEncoder() {

        String passwd = "easy-otc";

        String encode = PasswdUtil.encode(passwd);
        System.out.println(encode);
        System.out.println(PasswdUtil.verify(passwd, encode));
    }
}
