package com.easytech.otc.common;

import java.util.regex.Pattern;

import com.easytech.otc.config.PropertiesConfig;

/**
 * 验证手机号，新加号段
 */
public class MobileVerifyUtil {

    // 开头1第二位3,4,5,7,8,9
    public static Pattern MOBILR_VERIFY   = Pattern.compile("^1[3|4|5|7|8|9][0-9]{9}$");
    // 166号段支持
    public static Pattern MOBILR_VERIFY_2 = Pattern.compile("^166[0-9]{8}$");

    /**
     * 简单校验手机号
     *
     * @param mobile
     * @return
     */
    public static boolean verifyMobile(String mobile) {
        if (mobile != null && mobile.trim().length() == 11) {
            return MOBILR_VERIFY.matcher(mobile).matches() || MOBILR_VERIFY_2.matcher(mobile).matches();
        }
        return false;
    }

    /**
     * 生成验证码
     *
     * @return
     */
    public static String genMobileCode() {

        if (PropertiesConfig.isMock()) {
            return "123456";
        } else {
            String vcode = "";
            for (int i = 0; i < 6; i++) {
                vcode = vcode + (int) (Math.random() * 9);
            }
            return vcode;

        }
    }
}