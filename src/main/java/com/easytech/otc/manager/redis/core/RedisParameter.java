package com.easytech.otc.manager.redis.core;

import com.easytech.otc.manager.redis.KeyType;
import com.easytech.otc.manager.redis.RedisKey;

import java.io.Serializable;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:20
 */

public class RedisParameter {

    private RedisKey redisKey = null;
    private OpType op = null;
    private KeyType keyType = null;
    private Serializable keyId = null;
    private Object[] values = null;

    public RedisParameter() {

    }

    public RedisParameter(RedisKey key) {

    }

    public RedisParameter(RedisKey key, Serializable id, Object... values) {
        this.redisKey = key;
        this.keyType = key.keyType();
        this.values = values;
        this.op = OpType.QUERY;
        this.keyId = id;
    }

    public RedisKey getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(RedisKey redisKey) {
        this.redisKey = redisKey;
    }

    public OpType getOp() {
        return op;
    }

    public void setOp(OpType op) {
        this.op = op;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public Serializable getKeyId() {
        return keyId;
    }

    public void setKeyId(Serializable keyId) {
        this.keyId = keyId;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public String buildKey(Serializable keyId) {
        String namespace = redisKey.namespace();
        if (namespace == null || namespace.length() == 0) {
            throw new IllegalArgumentException("The return value of RedisKey#namespace() COULD NOT BE EMPTY.");
        }
        return namespace + RedisKey.KEY_SEPARATOR +
                redisKey.keyName() + RedisKey.KEY_ID_SEPARATOR +
                keyId;
    }

    public String buildKey() {
        return buildKey(getKeyId());
    }




}

