package com.easytech.otc.coin.support;

import java.math.BigDecimal;

import com.easytech.otc.enums.CoinTypeEnum;

public interface CoinOperator {

    /**
     * 币种
     * 
     * @return
     */
    CoinTypeEnum coinType();

    /**
     * 生成账户
     *
     * @return
     */
    KeyAddr genKeyAddr();

    /**
     * 发送交易
     *
     * @param from
     * @param to
     * @param amount 一定要注意计量单位
     * @param supplement
     * @param privateKey
     * @return
     */
    String sendTransaction(String from, String to, BigDecimal amount, String supplement, String privateKey);
}
