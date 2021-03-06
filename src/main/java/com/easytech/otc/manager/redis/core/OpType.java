package com.easytech.otc.manager.redis.core;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:18
 */

public enum OpType {
                    CHANGE, // add，del，set，remove，自增，自减等更改数据的操作类型，这种类型的操作如果缓存是持久化的，则需要更新持久化的数据。
                    QUERY, // 读、查询类操作
                    EXPIRE, // 设置过期时间
                    DEL // 删除操作
}
