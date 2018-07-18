package com.easytech.common;

import com.alibaba.fastjson.serializer.ValueFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * fastjson插件，自定义BigDecimal序列化
 */
public class BigDecimalValueFilter implements ValueFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalValueFilter.class);

    @Override
    public Object process(Object object, String name, Object value) {
        try {
            if (object != null && value != null && value instanceof BigDecimal) {
                BigDecimal v = (BigDecimal) value;
                return v.toString();
            }
        } catch (Exception e) {
            LOGGER.error("bigdecimal value filter err", e);
        }

        return value;
    }
}