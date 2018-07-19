package com.easytech.otc.manager.redis;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:23
 */

public enum KeyType {
    STRING(),                   // 字符串
    HASH(),                       //  哈希表
    LIST(),                       // 列表
    SET(),                         // 集合
    SORTED_SET(),            // 有序集合
}
