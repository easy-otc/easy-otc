package com.easytech.otc.coin.support.eth;

import com.easytech.otc.exception.BizException;
import com.easytech.otc.mvc.protocol.RetCodeEnum;

public class EthException extends BizException {
    public EthException() {
    }

    public EthException(RetCodeEnum bizExceptionEnum) {
        super(bizExceptionEnum);
    }

    public EthException(String message) {
        super(message);
    }

    public EthException(String message, Throwable cause) {
        super(message, cause);
    }

    public EthException(Throwable cause) {
        super(cause);
    }

    public EthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
