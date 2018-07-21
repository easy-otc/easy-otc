package com.easytech.otc.cache;

import com.easytech.otc.manager.redis.KeyType;
import com.easytech.otc.manager.redis.RedisKey;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/20 20:53
 */

public enum CodeKey implements RedisKey {
                                         VERIFY_CODE(3 * 60, KeyType.HASH, "短信验证码"), SECRET_KEY(3 * 60, KeyType.HASH, "临时公私钥");
    private static final String NAMESPACE = "code";

    private int                 expire    = -1;
    private KeyType             keyType   = null;
    private String              desc      = null;

    CodeKey(int expire, KeyType keyType, String desc) {
        this.expire = expire;
        this.keyType = keyType;
        this.desc = desc;
    }

    CodeKey(KeyType keyType, String desc) {
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
