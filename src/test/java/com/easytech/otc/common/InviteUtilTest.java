package com.easytech.otc.common;

import org.junit.Test;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/21 13:06
 */

public class InviteUtilTest {
    @Test
    public void getCodeByUid() {
        for (int i = 0; i < 100000; ++i) {
            System.out.println(InviteUtil.getCodeByUid(i));
        }
    }
}
