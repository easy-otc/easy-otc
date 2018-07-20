package com.easytech.otc.exception;

import com.easytech.otc.mvc.protocol.RetCodeEnum;

/**
 * 通用业务异常
 */
public class BizException extends RuntimeException {

    private RetCodeEnum bizExceptionEnum;

    public BizException() {
    }

    public BizException(RetCodeEnum bizExceptionEnum) {
        this.bizExceptionEnum = bizExceptionEnum;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RetCodeEnum getBizExceptionEnum() {
        return bizExceptionEnum;
    }
}
