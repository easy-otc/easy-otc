package com.easytech.otc.enums;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 币种
 */
@Getter
@AllArgsConstructor
public enum CoinTypeEnum {

    BITCOIN("0000", "比特币", null, null, null, null),
    ETHEREUM("0100", "以太坊", null, null, null, null),
        TRONIX("0101", "波场", "TRX", "0xf230b790E05390FC8295F4d3F60332c93BEd42e2", 10, new BigInteger("100,000,000,000".replace(",",""))),;

    /**
     * 前两位表示公链，后两位为00代表公链币，否则代表代币号
     *
     * 公链币只有code、desc
     */
    String     code;
    String     desc;
    /**
     *
     */
    String     symbol;
    /**
     * 合约地址
     */
    String     contractAddress;
    /**
     * 精度
     */
    Integer    decimals;
    /**
     * 发行总量
     */
    BigInteger totalSupply;

}