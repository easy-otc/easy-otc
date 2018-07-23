package com.easytech.otc.enums;

import lombok.Getter;

@Getter
public enum PayModeEnum {

    ZFB(0, "支付宝"), WX(1, "微信"), BANK_CARD(2, "银行卡");

    int    code;
    String desc;

    PayModeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
