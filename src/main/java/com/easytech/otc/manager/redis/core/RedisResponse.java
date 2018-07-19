package com.easytech.otc.manager.redis.core;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:25
 */

public class RedisResponse<T> {
    private boolean isAllDown = false;
    private T result = null;

    public RedisResponse() {

    }

    public RedisResponse(T result) {
        this.result = result;
    }

    public boolean isAllDown() {
        return isAllDown;
    }

    public void setAllDown(boolean allDown) {
        isAllDown = allDown;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RedisResponse{" +
                "isAllDown=" + isAllDown +
                ", result=" + result +
                '}';
    }
}
