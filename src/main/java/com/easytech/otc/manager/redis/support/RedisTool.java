package com.easytech.otc.manager.redis.support;

import com.easytech.otc.cache.DemoKey;
import com.easytech.otc.manager.redis.RedisKey;
import com.easytech.otc.manager.redis.core.RedisConfig;
import com.easytech.otc.manager.redis.core.RedisParameter;
import com.easytech.otc.manager.redis.core.RedisResponse;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:32
 */

public class RedisTool extends RedisExecutor {

    public RedisTool(String host,int port){
        super(new RedisConfig(host,port));
    }

    public RedisTool(RedisConfig... configs) {
        super(configs);
    }

    public Boolean exists(RedisKey key, Serializable id) {
        RedisResponse<Boolean> setResult = execute(new RedisParameter(key, id), Tool.exists());
        return setResult.getResult();
    }

    public Long expire(RedisKey key, Serializable id, int seconds) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, seconds), Tool.expire());
        return result.getResult();
    }

    public Long TTL(RedisKey key, Serializable id) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id), Tool.TTL());
        return setResult.getResult();
    }

    public Long incr(RedisKey key, Serializable id) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id), Tool.incr());
        return setResult.getResult();
    }

    public Long incrBy(RedisKey key, Serializable id, long value) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id, value), Tool.incrBy());
        return setResult.getResult();
    }

    public Long decr(RedisKey key, Serializable id) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id), Tool.decr());
        return setResult.getResult();
    }

    public Long decrBy(RedisKey key, Serializable id, long value) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id, value), Tool.decrBy());
        return setResult.getResult();
    }

    public Long del(RedisKey key, Serializable id) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id), Tool.del());
        return setResult.getResult();
    }

    /**
     * set
     *
     * @param key key
     * @param id  key id
     * @param val set value
     * @return 如果操作成功，那么返回值为true，否则返回值为false
     */
    public boolean set(RedisKey key, Serializable id, String val) {
        RedisResponse<String> setResult = execute(new RedisParameter(key, id, val), Tool.set());
        return "OK".equalsIgnoreCase(setResult.getResult());
    }

    /**
     * get
     *
     * @param key key
     * @param id  key id
     * @return 如果操作成功，那么获得key对应的值，否则返回值为null
     */
    public String get(RedisKey key, Serializable id) {
        RedisResponse<String> result = execute(new RedisParameter(key, id), Tool.get());
        return result.getResult();
    }

    public String hget(RedisKey key, Serializable id, String middleKey) {
        RedisResponse<String> result = execute(new RedisParameter(key, id, middleKey), Tool.hget());
        return result.getResult();
    }

    public String hmset(RedisKey key, Serializable id, Map<String, String> map) {
        RedisResponse<String> result = execute(new RedisParameter(key, id, map), Tool.hmset());
        return result.getResult();
    }

    public Long hset(RedisKey key, Serializable id, String middleKey, String value) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, middleKey, value), Tool.hset());
        return result.getResult();
    }

    public List<String> hmget(RedisKey key, Serializable id, String... fields) {
        RedisResponse<List<String>> result = execute(new RedisParameter(key, id, fields), Tool.hmget());
        return result.getResult();
    }

    public Long hlen(RedisKey key, Serializable id) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id), Tool.hlen());
        return result.getResult();
    }

    public Long hdel(RedisKey key, Serializable id, String... fields) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, fields), Tool.hdel());
        return result.getResult();
    }

    public Map<String, String> hgetAll(RedisKey key, Serializable id) {
        RedisResponse<Map<String, String>> result = execute(new RedisParameter(key, id), Tool.hgetAll());
        return result.getResult();
    }

    public Boolean hexists(RedisKey key, Serializable id, String field) {
        RedisResponse<Boolean> result = execute(new RedisParameter(key, id, field), Tool.hexists());
        return result.getResult();
    }

    public Long hincrby(RedisKey key, Serializable id, String field, long value) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, field, value), Tool.hincrby());
        return result.getResult();
    }

    public Long rpush(RedisKey key, Serializable id, String... val) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id, val), Tool.rpush());
        return setResult.getResult();
    }


    public Long lpush(RedisKey key, Serializable id, String... val) {
        RedisResponse<Long> setResult = execute(new RedisParameter(key, id, val), Tool.lpush());
        return setResult.getResult();
    }

    public String rpop(RedisKey key, Serializable id) {
        RedisResponse<String> result = execute(new RedisParameter(key, id), Tool.rpop());
        return result.getResult();
    }

    public String lpop(RedisKey key, Serializable id) {
        RedisResponse<String> result = execute(new RedisParameter(key, id), Tool.lpop());
        return result.getResult();
    }

    public Long llen(RedisKey key, Serializable id) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id), Tool.llen());
        return result.getResult();
    }

    public List<String> lrang(RedisKey key, Serializable id, long start, long end) {
        RedisResponse<List<String>> result = execute(new RedisParameter(key, id, start, end), Tool.lrange());
        return result.getResult();
    }

    public String lindex(RedisKey key, Serializable id, long index) {
        RedisResponse<String> result = execute(new RedisParameter(key, id, index), Tool.lindex());
        return result.getResult();
    }

    public Long lrem(RedisKey key, Serializable id, long count, String value) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, count, value), Tool.lrem());
        return result.getResult();
    }

    public String lset(RedisKey key, Serializable id, long index, String value) {
        RedisResponse<String> result = execute(new RedisParameter(key, id, index, value), Tool.lset());
        return result.getResult();
    }

    public Long sadd(RedisKey key, Serializable id, String value) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, value), Tool.sadd());
        return result.getResult();
    }

    public String spop(RedisKey key, Serializable id) {
        RedisResponse<String> result = execute(new RedisParameter(key, id), Tool.spop());
        return result.getResult();
    }

    public Set<String> smembers(RedisKey key, Serializable id) {
        RedisResponse<Set<String>> result = execute(new RedisParameter(key, id), Tool.smembers());
        return result.getResult();
    }

    public Boolean sismember(RedisKey key, Serializable id, String value) {
        RedisResponse<Boolean> result = execute(new RedisParameter(key, id, value), Tool.sismember());
        return result.getResult();
    }

    public Long scard(RedisKey key, Serializable id) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id), Tool.scard());
        return result.getResult();
    }

    public String srandmember(RedisKey key, Serializable id) {
        RedisResponse<String> result = execute(new RedisParameter(key, id), Tool.srandmember());
        return result.getResult();
    }

    public Long srem(RedisKey key, Serializable id, String... members) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, members), Tool.srem());
        return result.getResult();
    }

    public List<String> sort(RedisKey key, Serializable id) {
        RedisResponse<List<String>> result = execute(new RedisParameter(key, id), Tool.sort());
        return result.getResult();
    }

    public List<String> sort(RedisKey key, Serializable id, SortingParams sortingParams) {
        RedisResponse<List<String>> result = execute(new RedisParameter(key, id, sortingParams), Tool.sortByParam());
        return result.getResult();
    }

    public Long zadd(RedisKey key, Serializable id, double score, String member) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, score, member), Tool.zadd());
        return result.getResult();
    }

    public Long zadd(RedisKey key, Serializable id, Map<String, Double> map) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, map), Tool.zaddMap());
        return result.getResult();
    }

    public Long zcard(RedisKey key, Serializable id) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id), Tool.zcard());
        return result.getResult();
    }

    public Double zincrby(RedisKey key, Serializable id, double score, String member) {
        RedisResponse<Double> result = execute(new RedisParameter(key, id, score, member), Tool.zincrby());
        return result.getResult();
    }

    public Set<String> zrange(RedisKey key, Serializable id, long start, long end) {
        RedisResponse<Set<String>> result = execute(new RedisParameter(key, id, start, end), Tool.zrange());
        return result.getResult();
    }

    public Set<Tuple> zrangeWithScores(RedisKey key, Serializable id, long min, long max) {
        RedisResponse<Set<Tuple>> result = execute(new RedisParameter(key, id, min, max), Tool.zrangeWithScores());
        return result.getResult();
    }

    public Set<String> zrevrange(RedisKey key, Serializable id, long start, long end) {
        RedisResponse<Set<String>> result = execute(new RedisParameter(key, id, start, end), Tool.zrevrange());
        return result.getResult();
    }

    public Set<Tuple> zrevrangeWithScores(RedisKey key, Serializable id, long min, long max) {
        RedisResponse<Set<Tuple>> result = execute(new RedisParameter(key, id, min, max), Tool.zrevrangeWithScores());
        return result.getResult();
    }

    public Long zrem(RedisKey key, Serializable id, String... members) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, members), Tool.zrem());
        return result.getResult();
    }

    public Long zremrangebyrank(RedisKey key, Serializable id, long start, long end) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, start, end), Tool.zremrangebyrank());
        return result.getResult();
    }

    public Long zremrangebyscore(RedisKey key, Serializable id, long start, long end) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, start, end), Tool.zremrangebyscore());
        return result.getResult();
    }

    public Long hsetnx(RedisKey key, Serializable id, String field, String member) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, field, member), Tool.hsetnx());
        return result.getResult();
    }

    public Long setnx(RedisKey key, Serializable id, String value) {
        RedisResponse<Long> result = execute(new RedisParameter(key, id, value), Tool.setnx());
        return result.getResult();
    }

    public static void main(String[] args) {
        RedisTool redisTool = new RedisTool(new RedisConfig("127.0.0.1",6379));
        redisTool.set(DemoKey.demo,"1","cch");
    }
}
