package com.easytech.otc.manager.redis;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:23
 */

public interface RedisKey {
    String KEY_SEPARATOR = ".";
    String KEY_ID_SEPARATOR = "#";
    String KEY_ID_SEPARATOR_COMPATIBLE = "`@~";

    /**
     * redis key 的命名空间，最终为作为redis key 的
     * <p>
     * namespace.keyName#id 作为组合redis key
     *
     * @return key的命名空间
     */
    String namespace();

    /**
     * 获得key的类型，决定了在下层操作是否会进行设置超时时间等操作
     *
     * @return redis key类型
     */
    KeyType keyType();

    /**
     * 获得redis key 的超时设置
     * <p>
     * > 0 设置超时，-1在更新时不设置超时
     *
     * @return 超时设置
     */
    int expire();

    /**
     * 在namespace下，key的名称
     * namespace.keyName#id 组合键
     *
     * @return key name
     */
    String keyName();

    /**
     * 设置redis 操作的配置组，允许在一个项目中使用多个redis实例
     *
     * @return 服务组的名称
     */
    String serverGroup();
}

