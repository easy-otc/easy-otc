
package com.easytech.otc.mvc.protocol;

/**
 */
public class RequestContext {

    // 请求用户信息
    private static final ThreadLocal<AuthedInfo> AUTHED_INFO = new ThreadLocal<>();
    // 请求ip地址
    private static final ThreadLocal<String>     IP          = new ThreadLocal<>();

    public static void setAuthedInfo(AuthedInfo authedInfo) {
        AUTHED_INFO.set(authedInfo);
    }

    public static <T> AuthedInfo getAuthedInfo() {
        return AUTHED_INFO.get();
    }

    public static void setIp(String ip) {
        IP.set(ip);
    }

    public static String getIp() {
        return IP.get();
    }

    public static void clear() {
        AUTHED_INFO.remove();
        IP.remove();
    }
}
