package com.easytech.otc.mvc.protocol;

import java.lang.annotation.*;

/**
 * 访问控制注解
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ACL {

    /**
     * 是否身份访问控制
     *
     * @return
     */
    boolean authControl() default true;

    /**
     * 身份标识Key
     *
     * @return
     */
    String authTagKey() default "uid";

    /**
     * 是否幂等接口
     *
     * @return
     */
    boolean idempotent() default false;

}
