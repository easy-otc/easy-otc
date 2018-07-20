package com.easytech.otc.cache;

import com.easytech.otc.manager.redis.KeyType;
import com.easytech.otc.manager.redis.RedisKey;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:53
 */

public enum DemoKey implements RedisKey {
                                         demo(24 * 3600 * 2, KeyType.STRING, "test");
    private static final String NAMESPACE = "DEMO";

    private int                 expire    = -1;
    private KeyType             keyType   = null;
    private String              desc      = null;

    DemoKey(int expire, KeyType keyType, String desc) {
        this.expire = expire;
        this.keyType = keyType;
        this.desc = desc;
    }

    DemoKey(KeyType keyType, String desc) {
        this.keyType = keyType;
        this.desc = desc;
    }

    @Override
    public String namespace() {
        return NAMESPACE;
    }

    @Override
    public KeyType keyType() {
        return null;
    }

    @Override
    public int expire() {
        return this.expire;
    }

    @Override
    public String keyName() {
        return this.name();
    }

    @Override
    public String serverGroup() {
        return null;
    }
}
