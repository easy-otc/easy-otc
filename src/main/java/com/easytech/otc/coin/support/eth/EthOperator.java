package com.easytech.otc.coin.support.eth;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easytech.otc.coin.support.CoinOperator;
import com.easytech.otc.coin.support.KeyAddr;
import com.easytech.otc.coin.support.eth.base.TransactionClient;
import com.easytech.otc.coin.support.eth.base.WalletClient;
import com.easytech.otc.enums.CoinTypeEnum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EthOperator implements CoinOperator {

    @Autowired
    private WalletClient       walletClient;
    @Autowired
    private TransactionClient  transactionClient;
    @Autowired
    private TransactionChecker transactionChecker;

    @Override
    public CoinTypeEnum coinType() {
        return CoinTypeEnum.ETHEREUM;
    }

    /**
     * 生成账户
     *
     * @return
     */
    @Override
    public KeyAddr genKeyAddr() {
        return walletClient.createKeyAddr();
    }

    /**
     * 发送交易
     *
     * @param from
     * @param to
     * @param amount 单位：ETH
     * @param supplement
     * @param privateKey
     * @return 交易hash
     */
    @Override
    public String sendTransaction(String from, String to, BigDecimal amount, String supplement, String privateKey) {

        String transactionHash;
        try {
            if (StringUtils.isBlank(supplement)) {
                supplement = EthValues.EMPTY_STR;
            }

            transactionHash = transactionClient.sendTransaction(from, to, amount, EthValues.getGasPrice(), EthValues.getGasLimit(), supplement, EthValues.CHAIN_ID, privateKey);
        } catch (Exception e) {
            LOGGER.error("发送交易失败", e);
            return null;
        }

        transactionChecker.submit(transactionHash);
        return transactionHash;
    }

    /**
     * 发送代币交易
     * 
     * @param contractAddress
     * @param from
     * @param to
     * @param amount 单位：个
     * @param privateKey
     * @return
     */
    @Override
    public String sendTokenTransaction(String contractAddress, String from, String to, BigDecimal amount, String privateKey) {
        try {
            return transactionClient.sendTokenTransaction(contractAddress, from, to, amount, coinType().getDecimals(), EthValues.getGasPrice(), EthValues.getGasLimit(), EthValues.CHAIN_ID,
                privateKey);
        } catch (Exception e) {
            LOGGER.error("发送代币交易失败", e);
            return null;
        }
    }
}