package com.easytech.otc.enums;

import lombok.Getter;

/**
 * 币种
 * <p>
 * 0-比特币，1-以太坊
 */
@Getter
public enum CoinTypeEnum {

    BITCOIN(0, "比特币"), ETHEREUM(1, "以太坊");

    int    code;
    String desc;

    CoinTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}