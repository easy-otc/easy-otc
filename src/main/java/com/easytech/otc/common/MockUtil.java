package com.easytech.otc.common;

public class MockUtil {

    private static boolean mock = false;

    public synchronized static boolean isMock() {
        try {
            mock = Boolean.parseBoolean(System.getenv("MOCK"));
            if (!mock) {
                mock = Boolean.parseBoolean(System.getProperty("MOCK"));
            }
        } catch (Exception e) {
        }

        return mock;
    }
}