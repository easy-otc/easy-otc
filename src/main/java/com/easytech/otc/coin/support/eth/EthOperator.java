package com.easytech.otc.coin.support.eth;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easytech.otc.coin.support.CoinOperator;
import com.easytech.otc.coin.support.KeyAddr;
import com.easytech.otc.coin.support.eth.base.TransactionChecker;
import com.easytech.otc.coin.support.eth.base.TransactionClient;
import com.easytech.otc.coin.support.eth.base.WalletClient;
import com.easytech.otc.enums.CoinTypeEnum;
import com.easytech.otc.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EthOperator implements CoinOperator {

    @Autowired
    private WalletClient walletClient;
    @Autowired
    private TransactionClient transactionClient;
    @Autowired
    private TransactionChecker transactionChecker;

    @Autowired
    private OrderService       orderService;

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
     * @param amount 单位 ETH
     * @param supplement
     * @param privateKey
     * @return 交易hash
     */
    @Override
    public String sendTransaction(String from, String to, BigDecimal amount, String supplement, String privateKey) {

        String transactionHash = null;
        try {
            BigInteger gasPrice = EthValues.getInstance().getGasPrice();
            byte chainId = EthValues.getInstance().getChainId();

            if (StringUtils.isBlank(supplement)) {
                supplement = EthValues.EMPTY_STR;
            }

            transactionHash = transactionClient.sendTransaction(from, to, gasPrice, amount, supplement, privateKey, chainId);
        } catch (Exception e) {
            LOGGER.error("发送交易失败", e);
            return null;
        }

        transactionChecker.submit(transactionHash);
        return transactionHash;
    }
}