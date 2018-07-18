package com.easytech.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被标记的类或者字段，其返回给前端的Bigdecimal字段会被四舍五入转成String类型
 * 
 * Created by lwz on 2018/01/31 11:29.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalToString {

    String[] ignoreFields() default "";
}
