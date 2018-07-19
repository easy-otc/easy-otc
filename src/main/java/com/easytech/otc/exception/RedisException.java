package com.easytech.otc.exception;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:31
 */

public class RedisException extends RuntimeException {
    public RedisException() {
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }
}
