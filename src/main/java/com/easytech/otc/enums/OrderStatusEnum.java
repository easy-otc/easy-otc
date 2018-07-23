package com.easytech.otc.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 */
@Getter
public enum OrderStatusEnum {

    ORDERED(0, "已下单"), PAYED(1, "已支付"), CANCELLED(2, "已取消"), FINISHED(3, "已完成");

    int    code;
    String desc;

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}