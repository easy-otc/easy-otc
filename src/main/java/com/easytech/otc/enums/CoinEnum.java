package com.easytech.otc.enums;

import lombok.Getter;

/**
 * 币种
 * <p>
 * 0-比特币，1-以太坊，2-以太坊代币
 */
@Getter
public enum CoinEnum {

                      BITCOIN(0, "比特币"), ETHEREUM(1, "以太坊"), ETHEREUM_TOKEN(2, "以太坊代币");

    int    code;
    String desc;

    CoinEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}