package com.easytech.otc.common;

import org.junit.Test;

import com.easytech.otc.common.crypt.AESUtil;

public class AESUtilTest {

    @Test
    public void test() throws Exception {
        String data = "passwd-112";
        String sKey = "afddfkld+-23=dsf";
        String ivParameter = "dfdfdkfeer=_=-+f";

        String encrypt = AESUtil.getInstance().encrypt0(data, sKey, ivParameter);

        System.out.println(encrypt);

        String decrypt = AESUtil.getInstance().decrypt0(encrypt, sKey, ivParameter);
        System.out.println(decrypt);
    }
}
