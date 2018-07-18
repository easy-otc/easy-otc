package com.easytech.otc.enums;

import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum OrderStatusEnum {

    PROCESSING(0, "进行中"), FINISHED(1, "已完成"), CANCELLED(1, "已取消");


    int code;
    String desc;

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
