package com.easytech.otc.mvc.controller;

import com.easytech.otc.common.crypt.RSAUtils;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

public class Cache {

    private static final ConcurrentMap<String, RSAUtils.K> TMP_KEYS = Maps.newConcurrentMap();

    public static void putTmpKey(String mobile, RSAUtils.K k) {
        TMP_KEYS.put(mobile, k);
    }

    public static RSAUtils.K getTmpKey(String mobile) {
        return TMP_KEYS.get(mobile);
    }

}
