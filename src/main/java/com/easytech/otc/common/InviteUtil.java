package com.easytech.otc.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/20 23:35
 */

public class InviteUtil {
    private static final String  sourceString = "bnm012kjtyplhgfdq5sa3rzxcv8w6e497iuo";
    private static final Pattern pattern      = Pattern.compile("^[A-Za-z0-9]{5,}$");

    /**
     * 通过用户id获取邀请码
     * @param uid
     * @return
     * @throws Exception
     */
    public static String getCodeByUid(Integer uid) {

        if (uid == null || uid < 0) {
            return null;
        }

        StringBuffer code = new StringBuffer();
        while (uid != 0) {
            int num = uid / 36;
            int remainder = uid - 36 * num;
            code.append(sourceString.charAt(remainder));
            uid = num;
        }
        return code.reverse().toString();
    }

    /**
     * 通过邀请码获得用户id
     * @param code
     * @return
     * @throws Exception
     */
    public static Integer getUidByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        if (!check(code)) {
            throw new IllegalArgumentException("邀请码错误");
        }

        char[] codeChars = code.toCharArray();
        int codeLength = code.length();
        StringBuffer uidStr = new StringBuffer();
        int result = 0;
        for (char codeChar : codeChars) {
            int value = sourceString.indexOf(codeChar);
            if (value == -1) {
                return null;
            }
            result = result + (int) (value * Math.pow(36, --codeLength));
        }
        return result;
    }

    public static boolean check(String code) {
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }
}
