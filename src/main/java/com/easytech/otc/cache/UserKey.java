package com.easytech.otc.cache;

import com.easytech.otc.manager.redis.KeyType;
import com.easytech.otc.manager.redis.RedisKey;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/21 18:31
 */

public enum UserKey implements RedisKey {
                                         USER_INFO(30 * 60, KeyType.HASH, "用户信息");

    private static final String NAMESPACE = "user";

    private int                 expire    = -1;
    private KeyType             keyType   = null;
    private String              desc      = null;

    UserKey(int expire, KeyType keyType, String desc) {
        this.expire = expire;
        this.keyType = keyType;
        this.desc = desc;
    }

    UserKey(KeyType keyType, String desc) {
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
