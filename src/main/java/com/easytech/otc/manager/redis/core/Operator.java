package com.easytech.otc.manager.redis.core;

import redis.clients.jedis.Jedis;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:26
 */

public abstract class Operator<T> {

    /**
     * To execute a redis request
     *
     * @param jedis jedis
     * @param param Redis parameter
     * @return return the redis response
     */
    public abstract T exec(Jedis jedis, RedisParameter param);

    /**
     * set a timeout on the specified key.
     *
     * @param jedis jedis
     * @param param Redis parameter
     * @return return true when timeout was set.
     */
    public boolean expireKey(Jedis jedis, RedisParameter param) {

        try {
            // return true when the timeout was set.
            return 1 == jedis.expire(param.buildKey(), param.getRedisKey().expire());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
}
