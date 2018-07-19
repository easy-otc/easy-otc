package com.easytech.otc.manager.redis.support;

import com.easytech.otc.manager.ArrayUtils;
import com.easytech.otc.manager.redis.core.OpType;
import com.easytech.otc.manager.redis.core.Operator;
import com.easytech.otc.manager.redis.core.RedisParameter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:32
 */

class Tool {

    public static Operator<Boolean> exists() {
        return new Operator<Boolean>() {
            @Override
            public Boolean exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.exists(param.buildKey());
            }
        };
    }

    public static Operator<Long> TTL() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.ttl(param.buildKey());
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Operator<Long> incr() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.incr(param.buildKey());
            }
        };
    }

    public static Operator<Long> incrBy() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.incrBy(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()));
            }
        };
    }

    public static Operator<Long> decr() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.decr(param.buildKey());
            }
        };
    }

    public static Operator<Long> decrBy() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.decrBy(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()));
            }
        };
    }

    public static Operator<Long> del() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.DEL);
                return jedis.del(param.buildKey());
            }
        };
    }

    public static Operator<String> set() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.set(param.buildKey(), (String) param.getValues()[0]);
            }
        };
    }

    public static Operator<String> get() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.get(param.buildKey());
            }
        };
    }


    ///////////////////////////////////////////////////////////////////////////


    public static Operator<Long> hset() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.hset(param.buildKey(),
                        param.getValues()[0].toString(),
                        param.getValues()[1].toString());
            }
        };
    }

    public static Operator<String> hmset() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.hmset(param.buildKey(),
                        (Map<String, String>) param.getValues()[0]);
            }
        };
    }

    public static Operator<String> hget() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.hget(param.buildKey(), param.getValues()[0].toString());
            }
        };
    }

    public static Operator<List<String>> hmget() {
        return new Operator<List<String>>() {
            @Override
            public List<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.hmget(param.buildKey(), ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<Map<String, String>> hgetAll() {
        return new Operator<Map<String, String>>() {
            @Override
            public Map<String, String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.hgetAll(param.buildKey());
            }
        };
    }

    public static Operator<Boolean> hexists() {
        return new Operator<Boolean>() {
            @Override
            public Boolean exec(Jedis jedis, RedisParameter param) {
                return jedis.hexists(param.buildKey(), param.getValues()[0].toString());
            }
        };
    }

    public static Operator<Long> hincrby() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.hincrBy(param.buildKey(),
                        param.getValues()[0].toString(),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Long> hlen() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.hlen(param.buildKey());
            }
        };
    }

    public static Operator<Long> hdel() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.hdel(param.buildKey(), ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<Long> rpush() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.rpush(param.buildKey(), ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<Long> lpush() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.lpush(param.buildKey(), ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    /**
     * 按照列表索引获得列表的值
     *
     * @param compatible 是否兼容模式
     * @return -
     */
    public static Operator<String> lindex() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                return jedis.lindex(param.buildKey(), (Long) param.getValues()[0]);
            }
        };
    }

    public static Operator<String> rpop() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.rpop(param.buildKey());
            }
        };
    }

    public static Operator<String> lpop() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.lpop(param.buildKey());
            }
        };
    }

    public static Operator<Long> llen() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.llen(param.buildKey());
            }
        };
    }

    public static Operator<List<String>> lrange() {
        return new Operator<List<String>>() {
            @Override
            public List<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.lrange(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString())
                );
            }
        };
    }

    public static Operator<Long> lrem() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.lrem(param.buildKey(), Long.valueOf(param.getValues()[0].toString()),
                        param.getValues()[1].toString());
            }
        };
    }

    public static Operator<String> lset() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.lset(param.buildKey(), Long.valueOf(param.getValues()[0].toString()),
                        param.getValues()[1].toString());
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public static Operator<Long> sadd() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.sadd(param.buildKey(), ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<String> spop() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.spop(param.buildKey());
            }
        };
    }


    public static Operator<Set<String>> smembers() {
        return new Operator<Set<String>>() {
            @Override
            public Set<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.smembers(param.buildKey());
            }
        };
    }

    public static Operator<Boolean> sismember() {
        return new Operator<Boolean>() {
            @Override
            public Boolean exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.sismember(param.buildKey(),
                        param.getValues()[0].toString());
            }
        };
    }

    public static Operator<Long> scard() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.scard(param.buildKey());
            }
        };
    }

    public static Operator<String> srandmember() {
        return new Operator<String>() {
            @Override
            public String exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.srandmember(param.buildKey());
            }
        };
    }

    public static Operator<Long> srem() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.srem(param.buildKey(),
                        ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<List<String>> sort() {
        return new Operator<List<String>>() {
            @Override
            public List<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.sort(param.buildKey());
            }
        };
    }

    public static Operator<List<String>> sortByParam() {
        return new Operator<List<String>>() {
            @Override
            public List<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.sort(param.buildKey(),
                        (SortingParams) param.getValues()[0]);
            }
        };
    }

    public static Operator<Long> zadd() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zadd(param.buildKey(),
                        Double.valueOf(param.getValues()[0].toString()),
                        param.getValues()[1].toString());
            }
        };
    }

    public static Operator<Long> zaddMap() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zadd(param.buildKey(),
                        (Map<String, Double>) param.getValues()[0]);
            }
        };
    }

    public static Operator<Long> zcard() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.zcard(param.buildKey());
            }
        };
    }

    public static Operator<Double> zincrby() {
        return new Operator<Double>() {
            @Override
            public Double exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zincrby(param.buildKey(),
                        Double.valueOf(param.getValues()[0].toString()),
                        param.getValues()[1].toString());
            }
        };
    }

    public static Operator<Set<String>> zrange() {
        return new Operator<Set<String>>() {
            @Override
            public Set<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.zrange(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Set<Tuple>> zrangeWithScores() {
        return new Operator<Set<Tuple>>() {
            @Override
            public Set<Tuple> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.zrangeWithScores(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Set<String>> zrevrange() {
        return new Operator<Set<String>>() {
            @Override
            public Set<String> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.zrevrange(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Set<Tuple>> zrevrangeWithScores() {
        return new Operator<Set<Tuple>>() {
            @Override
            public Set<Tuple> exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.QUERY);
                return jedis.zrevrangeWithScores(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Long> zrem() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zrem(param.buildKey(),
                        ArrayUtils.objArr2StrArr(param.getValues()));
            }
        };
    }

    public static Operator<Long> zremrangebyrank() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zremrangeByRank(param.buildKey(),
                        Long.valueOf(param.getValues()[0].toString()),
                        Long.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Long> zremrangebyscore() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.zremrangeByScore(param.buildKey(),
                        Double.valueOf(param.getValues()[0].toString()),
                        Double.valueOf(param.getValues()[1].toString()));
            }
        };
    }

    public static Operator<Long> expire() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                return jedis.expire(param.buildKey(),
                        Integer.valueOf(param.getValues()[0].toString()));
            }
        };
    }

    public static Operator<Long> hsetnx() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.hsetnx(param.buildKey(),
                        param.getValues()[0].toString(),
                        param.getValues()[1].toString());
            }
        };
    }

    public static Operator<Long> setnx() {
        return new Operator<Long>() {
            @Override
            public Long exec(Jedis jedis, RedisParameter param) {
                param.setOp(OpType.CHANGE);
                return jedis.setnx(param.buildKey(),
                        param.getValues()[0].toString());
            }
        };
    }
}
