package com.easytech.otc.mvc.protocol;

/**
 * 自定义的请求头
 */
public interface HeaderNames {

    /**
     * 幂等号
     */
    String IDEMPOTENT    = "idempotent";

    /**
     * token
     */
    String AUTHORIZATION = "authorization";

    /**
     * uid
     */
    String IDENTITY      = "identity";

}